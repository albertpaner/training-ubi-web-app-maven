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

        HashMap<String, List<EvalCountDto>> evaluators = new HashMap<>();
        try {
            UtenteEvaluationService utenteEvaluationService = new UtenteEvaluationService(new UtenteDao());
            evaluators = utenteEvaluationService.getEvaluatorsOccupiedFree(3);
            utenteEvaluationService.equilibrateValutatori(evaluators, 3);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        List<EvalCountDto> evaluatorsOccupied = evaluators.get("occupati");
        List<EvalCountDto> evaluatorsFree = evaluators.get("disponibili");
        for (EvalCountDto evaluator : evaluatorsOccupied) {
            System.out.println(evaluator.getNome() + " " + evaluator.getCognome() + " " + evaluator.getCount());
        }
        System.out.println("--------------------------------------------------");
        for (EvalCountDto evaluator : evaluatorsFree) {
            System.out.println(evaluator.getNome() + " " + evaluator.getCognome() + " " + evaluator.getCount());
        }


    }

}