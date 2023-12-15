package application;

import com.itextpdf.text.DocumentException;
import model.dao.UtenteDao;
import services.user.DocCreationService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class App {

    public static void main(String args[]) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException, DocumentException, IOException {

        int utenteID = 13;
        DocCreationService docCreationService = new DocCreationService(new UtenteDao());
        docCreationService.pdfEvaluationCreate(utenteID);

    }



}