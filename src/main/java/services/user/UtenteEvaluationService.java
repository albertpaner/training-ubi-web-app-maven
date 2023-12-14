package services.user;

import model.bean.UtenteBean;
import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import model.dto.UtenteDto;
import services.UtenteService;
import utils.converters.CountConverter;
import utils.converters.UtenteConverter;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class UtenteEvaluationService extends UtenteService {

    public UtenteEvaluationService(UtenteDao utenteDao) {
        super(utenteDao);
    }

    /**
     * This method returns a list of users who are assigned to a given evaluator.
     *
     * @param evatorId The ID of the evaluator.
     * @return A list of users who are assigned to the given evaluator.
     * @throws SQLException If a database access error occurs.
     */
    private List<UtenteBean> findValuedByEvaluator(int evatorId) throws SQLException, ClassNotFoundException {
        List<UtenteBean> allUsers = utenteDao.findAll();
        List<UtenteBean> filteredUsers = allUsers.stream()
                .filter(user -> user.getRuoloId() == 2)
                .filter(user -> user.getValutatoreId() == evatorId)
                .filter(user -> !user.getFlgDel())
                .toList();
        return new ArrayList<>(filteredUsers);
    }

    /**
     * This method fetches all evaluators and their corresponding users from the database.
     * It returns a HashMap where the keys are evaluators and the values are lists of users valued by the evaluator.
     *
     * @return A HashMap where the keys are evaluators and the values are lists of users.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC Driver class is not found.
     */
    private HashMap<UtenteBean, List<UtenteBean>> fetchEvaluatorsAndValued() throws SQLException, ClassNotFoundException {

        HashMap<UtenteBean, List<UtenteBean>> lordsAndPeasants = new HashMap<>();

        List<UtenteBean> allUsers = utenteDao.findAll();

        List<UtenteBean> allEvaluators = allUsers.stream()
                .filter(user -> user.getRuoloId() == 1)
                .filter(user -> !user.getFlgDel())
                .toList();

        for (UtenteBean lord : allEvaluators) {
            List<UtenteBean> peasantsUnderLord = findValuedByEvaluator(lord.getUtenteId());
            lordsAndPeasants.put(lord, peasantsUnderLord);
        }

        return lordsAndPeasants;
    }

    /**
     * This method categorizes evaluators into two groups: those who have more users than a given threshold (occupied) and those who have less (available).
     * It returns a HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * Each EvalCountDto object represents an evaluator and contains the evaluator's ID, name, surname, and the count of users assigned to them.
     *
     * @param soglia The threshold value for the number of users.
     * @return A HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC Driver class is not found.
     */
    public HashMap<String, List<EvalCountDto>> getEvaluatorsOccupiedFree(int soglia)
            throws SQLException, ClassNotFoundException {

        HashMap<UtenteBean, List<UtenteBean>> lordsAndPeasants = fetchEvaluatorsAndValued();

        List<EvalCountDto> valutatoriOccupatiDto = new ArrayList<>();
        List<EvalCountDto> valutatoriDisponibiliDto = new ArrayList<>();

        for (UtenteBean lord : lordsAndPeasants.keySet()) {
            EvalCountDto evaluatorDto = CountConverter.beanToDto(lord);
            int howManyPeasants = (int) lordsAndPeasants.get(lord).stream()
                    .filter(user -> !user.getInSospeso())
                    .count();
            evaluatorDto.setCount(howManyPeasants);

            if (howManyPeasants >= soglia) {
                valutatoriOccupatiDto.add(evaluatorDto);
            } else {
                valutatoriDisponibiliDto.add(evaluatorDto);
            }
        }

        HashMap<String, List<EvalCountDto>> usersToShow = new HashMap<>();
        usersToShow.put("occupati", valutatoriOccupatiDto);
        usersToShow.put("disponibili", valutatoriDisponibiliDto);

        return usersToShow;
    }

    /**
     * This method reassigns users to evaluators based on a threshold value.
     * The occupied evaluator who has the most users is selected, and the users are reassigned to the free evaluator who has the least users.
     *
     * @param usersToShow A HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * @param soglia      The threshold value for the number of users.
     * @return The number of users who were reassigned.
     */
    private int rearrangeValutatori(HashMap<String, List<EvalCountDto>> usersToShow, int soglia) throws SQLException, ClassNotFoundException {

        int shuffledUsers = 0;
        List<EvalCountDto> valutatoriOccupatiDto = usersToShow.get("occupati");
        List<EvalCountDto> valutatoriDisponibiliDto = usersToShow.get("disponibili");

        if (valutatoriDisponibiliDto.isEmpty() || valutatoriOccupatiDto.isEmpty()) {
            return shuffledUsers;
        }

        valutatoriOccupatiDto.sort(Comparator.comparing(EvalCountDto::getCount).reversed());
        valutatoriDisponibiliDto.sort(Comparator.comparing(EvalCountDto::getCount));

        EvalCountDto evalMaxCount = valutatoriOccupatiDto.get(0);
        EvalCountDto evalMinCount = valutatoriDisponibiliDto.get(0);

        System.out.println("evalMaxCount: " + evalMaxCount);
        System.out.println("evalMinCount: " + evalMinCount);

        List<UtenteBean> utentiValutatiDa = findValuedByEvaluator(evalMaxCount.getUtenteId());
        utentiValutatiDa.sort(Comparator.comparing(UtenteBean::getDataNascita));

        if (utentiValutatiDa.size() <= soglia) {
            return 0;
        }

        for (int i = utentiValutatiDa.size() - 1; (i >= soglia); i--) {
            UtenteBean utenteChange = utentiValutatiDa.remove(i);

            utenteDao.update(Arrays.asList(
                    utenteChange.getEmail(),
                    utenteChange.getPassword(),
                    utenteChange.getRuoloId(),
                    utenteChange.getNome(),
                    utenteChange.getCognome(),
                    evalMinCount.getUtenteId(),
                    utenteChange.getDataNascita(),
                    utenteChange.getUtenteId(),
                    utenteChange.getInSospeso()
            ));

            evalMinCount.setCount(evalMinCount.getCount() + 1);

            shuffledUsers++;

            if (evalMinCount.getCount() == soglia) {
                break;
            }
        }


        return shuffledUsers;
    }

    /**
     * This method reassigns users to evaluators based on a threshold value.
     * If an evaluator has more users than the threshold, the method reassigns the excess users to evaluators who have less than the threshold number of users.
     * The reassignment is done based on the user's date of birth, with the youngest users being reassigned first.
     * The waiting list is also updated.
     *
     * @param usersToShow A HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * @param soglia      The threshold value for the number of users.
     * @return The number of users who were reassigned.
     */
    public int distributeValutatori(HashMap<String, List<EvalCountDto>> usersToShow, int soglia) throws SQLException, ClassNotFoundException {

        int totalShuffledUsers;

        List<UtenteBean> allUsersWaiting = new ArrayList<>(utenteDao.findAll()
                .stream()
                .filter(user -> user.getRuoloId() == 2)
                .filter(UtenteBean::getInSospeso)
                .filter(user -> !user.getFlgDel())
                .collect(Collectors.toList()));

        do {
            totalShuffledUsers = oldWaitingList(allUsersWaiting, usersToShow, soglia) + rearrangeValutatori(usersToShow, soglia);
        } while (totalShuffledUsers > 0);

        HashMap<String, List<EvalCountDto>> newUsersToShow = getEvaluatorsOccupiedFree(soglia);
        newWaitingList(newUsersToShow, soglia);

        return totalShuffledUsers;
    }

    /**
     * This method reassigns users from the waiting list to free evaluators based on a threshold value.
     *
     * @param allUsersWaiting
     * @param usersToShow     A HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * @param soglia          The threshold value for the number of users.
     * @return The number of users who were reassigned.
     */
    private int oldWaitingList(List<UtenteBean> allUsersWaiting, HashMap<String, List<EvalCountDto>> usersToShow, int soglia) throws SQLException, ClassNotFoundException {

        int waitingNoMore = 0;

        List<EvalCountDto> valutatoriDisponibiliDto = usersToShow.get("disponibili");
        if (valutatoriDisponibiliDto.isEmpty()) {
            return waitingNoMore;
        }

        valutatoriDisponibiliDto.sort(Comparator.comparing(EvalCountDto::getCount));
        EvalCountDto evalMinCount = valutatoriDisponibiliDto.get(0);


        allUsersWaiting.sort(Comparator.comparing(UtenteBean::getDataNascita));

        for (int i = allUsersWaiting.size() - 1; (i >= 0); i--) {
            UtenteBean utenteChange = allUsersWaiting.remove(i);

            utenteDao.update(Arrays.asList(
                    utenteChange.getEmail(),
                    utenteChange.getPassword(),
                    utenteChange.getRuoloId(),
                    utenteChange.getNome(),
                    utenteChange.getCognome(),
                    evalMinCount.getUtenteId(),
                    utenteChange.getDataNascita(),
                    utenteChange.getUtenteId(),
                    false
            ));
            evalMinCount.setCount(evalMinCount.getCount() + 1);
            waitingNoMore++;

            if (evalMinCount.getCount() == soglia) {
                break;
            }
        }

        return waitingNoMore;
    }

    /**
     *   This method creates a new waiting list of users who need to be valued by an evaluator.
     *
     * @param usersToShow A HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * @param soglia      The threshold value for the number of users.
     * @return The number of users who were reassigned.
     * */
    private int newWaitingList(HashMap<String, List<EvalCountDto>> usersToShow, int soglia) throws SQLException, ClassNotFoundException {
        int totalUsersWaiting = 0;

        List<EvalCountDto> valutatoriOccupatiDto = usersToShow.get("occupati");

        for (EvalCountDto valutatore : valutatoriOccupatiDto) {
            List<UtenteBean> utentiValutatiDa = findValuedByEvaluator(valutatore.getUtenteId());

            if (utentiValutatiDa.size() <= soglia) {
                continue;
            }

            for (int i = utentiValutatiDa.size() - 1; (i >= soglia); i--) {
                UtenteBean utenteChange = utentiValutatiDa.remove(i);

                utenteDao.update(Arrays.asList(
                        utenteChange.getEmail(),
                        utenteChange.getPassword(),
                        utenteChange.getRuoloId(),
                        utenteChange.getNome(),
                        utenteChange.getCognome(),
                        utenteChange.getValutatoreId(),
                        utenteChange.getDataNascita(),
                        utenteChange.getUtenteId(),
                        true
                ));
                totalUsersWaiting++;
            }
        }

        return totalUsersWaiting;
    }

    /**
     * This method returns a list of users who are in the waiting list.
     *
     * @return A list of users who are in the waiting list.
     * @throws SQLException           If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC Driver class is not found.
     */
    public List<UtenteDto> getWaitingList() throws SQLException, ClassNotFoundException {
        List<UtenteBean> allUsers = utenteDao.findAll();

        return allUsers.stream()
                .filter(UtenteBean::getInSospeso)
                .filter(user -> !user.getFlgDel())
                .map(UtenteConverter::toDto).collect(Collectors.toList());
    }

    public int getAverageValued() throws SQLException, ClassNotFoundException {
        int numberOfPeasants = 0;
        int numberOfLords = 0;

        HashMap<UtenteBean, List<UtenteBean>> lordsAndPeasants = fetchEvaluatorsAndValued();

        for (UtenteBean lord : lordsAndPeasants.keySet()) {
            List<UtenteBean> peasantsUnderLord = lordsAndPeasants.get(lord);
            peasantsUnderLord = peasantsUnderLord.stream()
                    .toList();
            numberOfPeasants += peasantsUnderLord.size();
            numberOfLords++;
        }

        return numberOfPeasants / numberOfLords;
    }


}
