package application;

import model.dao.UtenteDao;
import model.dto.EvalCountDto;
import services.user.ListService;


import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class App {

    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

        UtenteDao utenteDao = new UtenteDao();
        ListService listService = new ListService(utenteDao);
        //UtenteService utenteService = new UtenteService(utenteDao);

        int soglia = 2;
        HashMap<String, List<EvalCountDto>> evaluators = listService.getEvaluatorsOccupiedFree(soglia);
        List<EvalCountDto> freeEvaluators = evaluators.get("disponibili");
        List<EvalCountDto> occupiedEvaluators = evaluators.get("occupati");

        System.out.println("before equilibrateValutatori");
        System.out.println("occupiedEvaluators: " + occupiedEvaluators);
        System.out.println("--------------------------------------------");
        System.out.println("freeEvaluators: " + freeEvaluators);
        System.out.println("--------------------------------------------");
        System.out.println("waiting list: " + listService.getWaitingList());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        listService.distributeValutatori(evaluators, soglia);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

        evaluators = listService.getEvaluatorsOccupiedFree(soglia);
        freeEvaluators = evaluators.get("disponibili");
        occupiedEvaluators = evaluators.get("occupati");

        System.out.println("after equilibrateValutatori");
        System.out.println("occupiedEvaluators: " + occupiedEvaluators);
        System.out.println("--------------------------------------------");
        System.out.println("freeEvaluators: " + freeEvaluators);
        System.out.println("--------------------------------------------");
        System.out.println("waiting list: " + listService.getWaitingList());

    }

}