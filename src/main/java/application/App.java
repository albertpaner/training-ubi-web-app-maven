package application;

import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import services.PdfMakerService;
import services.user.UtenteEvaluationService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class App {

    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException, IOException {

        /*UtenteDao utenteDao = new UtenteDao();
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
        System.out.println("waiting list: " + utenteEvaluationService.getWaitingList());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        utenteEvaluationService.equilibrateValutatori(evaluators, soglia);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

        evaluators = utenteEvaluationService.getEvaluatorsOccupiedFree(soglia);
        freeEvaluators = evaluators.get("disponibili");
        occupiedEvaluators = evaluators.get("occupati");

        System.out.println("after equilibrateValutatori");
        System.out.println("occupiedEvaluators: " + occupiedEvaluators);
        System.out.println("--------------------------------------------");
        System.out.println("freeEvaluators: " + freeEvaluators);
        System.out.println("--------------------------------------------");
        System.out.println("waiting list: " + utenteEvaluationService.getWaitingList());
		*/
    	
    	PdfMakerService pdf = new PdfMakerService(new UtenteDao());    
    	pdf.CreatePdf("enrico.montemurro@bikinibottom.com");
    }

}