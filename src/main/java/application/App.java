package application;

import model.dao.UtenteDao;
import services.user.EvaluatorDisplayService;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class App {

    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {

        EvaluatorDisplayService evaluatorDisplayService = new EvaluatorDisplayService(new UtenteDao());
        evaluatorDisplayService.getValutatori().forEach(System.out::println);
        evaluatorDisplayService.removeFromEvaluator(16, "priest");
    }

}