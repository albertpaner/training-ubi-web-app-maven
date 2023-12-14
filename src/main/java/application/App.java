package application;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import model.bean.UtenteBean;
import model.dao.UtenteDao;
import model.dto.UtenteDto;
import services.user.UtenteEvaluationService;

public class App {

    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

	UtenteDao utenteDao = new UtenteDao();
	int u = utenteDao.valWithMoreCount();
	System.out.println(u);
	List<UtenteBean> b = utenteDao.inactiveForThreeDays(1);
	;
	System.out.println(b);
	UtenteEvaluationService provaEvaluationService = new UtenteEvaluationService(utenteDao);
	List<UtenteDto> i = provaEvaluationService.taskTwo();
	System.out.println(i);

	/*
	 * utenteEvaluationService = new UtenteEvaluationService(utenteDao);
	 * 
	 * int soglia = 2; HashMap<String, List<EvalCountDto>> evaluators =
	 * utenteEvaluationService.getEvaluatorsOccupiedFree(soglia); List<EvalCountDto>
	 * freeEvaluators = evaluators.get("disponibili"); List<EvalCountDto>
	 * occupiedEvaluators = evaluators.get("occupati");
	 * 
	 * System.out.println("before equilibrateValutatori");
	 * System.out.println("occupiedEvaluators: " + occupiedEvaluators);
	 * System.out.println("--------------------------------------------");
	 * System.out.println("freeEvaluators: " + freeEvaluators);
	 * System.out.println("--------------------------------------------");
	 * System.out.println("waiting list: " +
	 * utenteEvaluationService.getWaitingList());
	 * 
	 * System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
	 * utenteEvaluationService.distributeValutatori(evaluators, soglia);
	 * System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
	 * 
	 * evaluators = utenteEvaluationService.getEvaluatorsOccupiedFree(soglia);
	 * freeEvaluators = evaluators.get("disponibili"); occupiedEvaluators =
	 * evaluators.get("occupati");
	 * 
	 * System.out.println("after equilibrateValutatori");
	 * System.out.println("occupiedEvaluators: " + occupiedEvaluators);
	 * System.out.println("--------------------------------------------");
	 * System.out.println("freeEvaluators: " + freeEvaluators);
	 * System.out.println("--------------------------------------------");
	 * System.out.println("waiting list: " +
	 * utenteEvaluationService.getWaitingList());
	 */
    }

}