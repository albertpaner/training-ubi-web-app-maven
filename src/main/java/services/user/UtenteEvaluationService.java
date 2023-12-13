package services.user;

import model.bean.UtenteBean;
import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import services.UtenteService;
import utils.converters.CountConverter;

import java.sql.SQLException;
import java.util.*;

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
        //UtenteDao utenteDao = new UtenteDao();
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
            int howManyPeasants = lordsAndPeasants.get(lord).size();
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
     * If an evaluator has more users than the threshold, the method reassigns the excess users to evaluators who have less than the threshold number of users.
     * The reassignment is done based on the user's date of birth, with the youngest users being reassigned first.
     *
     * @param usersToShow A HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * @param soglia      The threshold value for the number of users.
     * @return The number of users who were reassigned.
     */
    public int rearrangeValutatori(HashMap<String, List<EvalCountDto>> usersToShow, int soglia) throws SQLException, ClassNotFoundException {

        int shuffledUsers = 0;
        List<EvalCountDto> valutatoriOccupatiDto = usersToShow.get("occupati");
        List<EvalCountDto> valutatoriDisponibiliDto = usersToShow.get("disponibili");

        valutatoriOccupatiDto.sort(Comparator.comparing(EvalCountDto::getCount).reversed());
        valutatoriDisponibiliDto.sort(Comparator.comparing(EvalCountDto::getCount));

        for (EvalCountDto valutatore : valutatoriOccupatiDto) {

            List<UtenteBean> utentiValutatiDa = findValuedByEvaluator(valutatore.getUtenteId());
            utentiValutatiDa.sort(Comparator.comparing(UtenteBean::getDataNascita));

            if (utentiValutatiDa.size() > soglia) {

                for (int i = utentiValutatiDa.size() - 1; (i >= soglia) && !valutatoriDisponibiliDto.isEmpty(); i--) {
                    UtenteBean utenteChange = utentiValutatiDa.remove(i);

                    EvalCountDto evalMinCountDto = valutatoriDisponibiliDto.get(0);

                    System.out.println("evalMinCountDto: " + evalMinCountDto);

                    utenteDao.update(Arrays.asList(
                            utenteChange.getEmail(),
                            utenteChange.getPassword(),
                            utenteChange.getRuoloId(),
                            utenteChange.getNome(),
                            utenteChange.getCognome(),
                            evalMinCountDto.getUtenteId(),
                            utenteChange.getDataNascita(),
                            utenteChange.getUtenteId(),
                            utenteChange.getInSospeso()
                    ));

                    evalMinCountDto.setCount(evalMinCountDto.getCount() + 1);
                    if (evalMinCountDto.getCount() == soglia) {
                        break;
                    }

                    shuffledUsers++;


                }
            }
        }
        return shuffledUsers;
    }

    /**
     * This method reassigns users to evaluators based on a threshold value.
     * If an evaluator has more users than the threshold, the method reassigns the excess users to evaluators who have less than the threshold number of users.
     * The reassignment is done based on the user's date of birth, with the youngest users being reassigned first.
     * The method repeats the reassignment process until all evaluators have the same number of users.
     *
     * @param usersToShow A HashMap where the keys are "valutatori_occupati" and "valutatori_disponibili", and the values are lists of EvalCountDto objects.
     * @param soglia      The threshold value for the number of users.
     * @return The total number of users who were reassigned.
     */
    public int equilibrateValutatori(HashMap<String, List<EvalCountDto>> usersToShow, int soglia) throws SQLException, ClassNotFoundException {
        int totalShuffledUsers = 0;
        int shuffledUsers;

        setEmptyWaitingList();

        do {
            shuffledUsers = rearrangeValutatori(usersToShow, soglia);
            totalShuffledUsers += shuffledUsers;
        } while (shuffledUsers > 0);

        return totalShuffledUsers;
    }

    public int setWaitingList(HashMap<String, List<EvalCountDto>> usersToShow, int soglia) throws SQLException, ClassNotFoundException {
        int totalUsersWaiting = 0;

        List<EvalCountDto> valutatoriOccupatiDto = usersToShow.get("occupati");

        for (EvalCountDto valutatore : valutatoriOccupatiDto) {
            List<UtenteBean> utentiValutatiDa = findValuedByEvaluator(valutatore.getUtenteId());

            if (utentiValutatiDa.size() > soglia) {
                for (int i = utentiValutatiDa.size() - 1; (i >= soglia); i--) {
                    UtenteBean utenteChange = utentiValutatiDa.remove(i);

                    utenteDao.update(Arrays.asList(
                            utenteChange.getEmail(),
                            utenteChange.getPassword(),
                            utenteChange.getRuoloId(),
                            utenteChange.getNome(),
                            utenteChange.getCognome(),
                            0,
                            utenteChange.getDataNascita(),
                            utenteChange.getUtenteId(),
                            true
                    ));
                    totalUsersWaiting++;
                }
            }
        }


        return totalUsersWaiting;
    }

    private void setEmptyWaitingList() throws SQLException, ClassNotFoundException {
        List<UtenteBean> allUsers = utenteDao.findAll();
        allUsers.stream()
                .filter(user -> user.getRuoloId() == 2)
                .filter(user -> !user.getFlgDel())
                .forEach(user -> {
                    try {
                        utenteDao.update(Arrays.asList(
                                user.getEmail(),
                                user.getPassword(),
                                user.getRuoloId(),
                                user.getNome(),
                                user.getCognome(),
                                user.getValutatoreId(),
                                user.getDataNascita(),
                                user.getUtenteId(),
                                false
                        ));
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                });
    }

}
