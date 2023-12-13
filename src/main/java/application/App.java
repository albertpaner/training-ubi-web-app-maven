package application;

import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import services.user.UtenteEvaluationService;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class App {

    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

        UtenteDao utenteDao = new UtenteDao();
        UtenteEvaluationService utenteEvaluationService = new UtenteEvaluationService(utenteDao);

        int soglia = 2;
        HashMap<String, List<EvalCountDto>> evaluators = utenteEvaluationService.getEvaluatorsOccupiedFree(soglia);
        List<EvalCountDto> freeEvaluators = evaluators.get("disponibili");
        List<EvalCountDto> occupiedEvaluators = evaluators.get("occupati");

        System.out.println("before equilibrateValutatori");
        System.out.println("occupiedEvaluators: " + occupiedEvaluators);
        System.out.println("--------------------------------------------");
        System.out.println("freeEvaluators: " + freeEvaluators);
        System.out.println("--------------------------------------------");

        int res = utenteEvaluationService.equilibrateValutatori(evaluators, soglia);
        System.out.println("res: " + res);

        evaluators = utenteEvaluationService.getEvaluatorsOccupiedFree(soglia);
        freeEvaluators = evaluators.get("disponibili");
        occupiedEvaluators = evaluators.get("occupati");

        System.out.println("after equilibrateValutatori");
        System.out.println("occupiedEvaluators: " + occupiedEvaluators);
        System.out.println("--------------------------------------------");
        System.out.println("freeEvaluators: " + freeEvaluators);
        System.out.println("--------------------------------------------");
        System.out.println("waiting list: " + utenteEvaluationService.getWaitingList());

    }

}