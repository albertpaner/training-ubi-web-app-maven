package application;

import model.bean.UtenteBean;
import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import services.user.UtenteEvaluationService;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class App {

    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

        UtenteDao utenteDao = new UtenteDao();
        UtenteEvaluationService utenteEvaluationService = new UtenteEvaluationService(utenteDao);
        HashMap<String, List<EvalCountDto>> evaluators = utenteEvaluationService.getEvaluatorsOccupiedFree(3);
        List<EvalCountDto> freeEvaluators = evaluators.get("disponibili");
        List<UtenteBean> allUsers = utenteDao.findAll();

        freeEvaluators.sort(Comparator.comparing(EvalCountDto::getCount));
        allUsers.sort(Comparator.comparing(UtenteBean::getDataNascita));
/*
        System.out.println("Free Evaluators sorted:"+ freeEvaluators);
        System.out.println("-----------------------------------");
        System.out.println("All Users sorted:"+ allUsers);
*/
        int res = utenteEvaluationService.equilibrateValutatori(evaluators, 3);
        System.out.println("res: " + res);
    }

}