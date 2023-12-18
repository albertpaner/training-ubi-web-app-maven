package application;

import com.itextpdf.text.DocumentException;
import model.dao.UtenteDao;
import services.user.PdfMakerService;

import java.io.IOException;
import java.sql.SQLException;

public class App {

    public static void main(String args[]) throws ClassNotFoundException, SQLException, DocumentException, IOException {

        PdfMakerService pdfMakerService = new PdfMakerService(new UtenteDao());
        pdfMakerService.CreatePdf("enrico.montemurro@bikinibottom.com");

    }



}