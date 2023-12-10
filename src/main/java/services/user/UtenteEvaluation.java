package services.user;

import model.bean.UtenteBean;
import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import services.UtenteService;
import utils.converters.CountConverter;

import java.sql.SQLException;
import java.util.*;

public class UtenteEvaluation extends UtenteService {

    public UtenteEvaluation(UtenteDao utenteDao) {
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
        return allUsers.stream()
                .filter(user -> user.getValutatoreId() == evatorId)
                .filter(user -> !user.getFlgDel())
                .toList();
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
        // UtenteDao utenteDao = new UtenteDao();
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

            if (howManyPeasants > soglia) {
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
     * @param usersToShow A HashMap of evaluators and their corresponding users.
     * @param soglia      The threshold value for the number of users an evaluator can have.
     * @throws SQLException If a database access error occurs.
     */
    public void rearrengeValutatori(HashMap<String, List<EvalCountDto>> usersToShow, int soglia) throws SQLException, ClassNotFoundException {

        List<EvalCountDto> valutatoriOccupatiDto = usersToShow.get("valutatori_occupati");
        List<EvalCountDto> valutatoriDisponibiliDto = usersToShow.get("valutatori_disponibili");
        int evatorId = 0;

        for (EvalCountDto valutatore : valutatoriOccupatiDto) {

            evatorId = valutatore.getValutatoreId();
            List<UtenteBean> utentiValutatiDa = findValuedByEvaluator(evatorId);
            sortingUtentiEta(utentiValutatiDa);

            if (utentiValutatiDa.size() > soglia) {

                for (int i = utentiValutatiDa.size() - 1; i >= soglia; i--) {
                    UtenteBean utenteChange = utentiValutatiDa.remove(i);

                    utenteDao.update(Arrays.asList(
                            utenteChange.getEmail(),
                            utenteChange.getPassword(),
                            utenteChange.getRuoloId(),
                            utenteChange.getNome(),
                            utenteChange.getCognome(),
                            valutatoriDisponibiliDto.get(0).getValutatoreId(),
                            utenteChange.getDataNascita(),
                            utenteChange.getUtenteId()
                    ));
                }
            }
        }

    }


    /**
     * This method sorts a list of UtenteBean objects based on their dataNascita (date of birth) attribute.
     * The sorting is done in ascending order, meaning the oldest user will be first.
     *
     * @param utentiToSort The list of UtenteBean objects to be sorted.
     */
    public void sortingUtentiEta(List<UtenteBean> utentiToSort) {
        //utentiToSort.sort((u1, u2) -> u1.getDataNascita().compareTo(u2.getDataNascita()));
        utentiToSort.sort(Comparator.comparing(UtenteBean::getDataNascita));
    }

}
