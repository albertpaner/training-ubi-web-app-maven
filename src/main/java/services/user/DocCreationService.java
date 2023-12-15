package services.user;

import com.itextpdf.text.DocumentException;
import model.bean.UtenteBean;
import model.dao.UtenteDao;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import services.UtenteService;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Year;
import java.util.Optional;


public class DocCreationService extends UtenteService {

    public DocCreationService(UtenteDao utenteDao) {
        super(utenteDao);
    }

    public void pdfEvaluationCreate(int utenteID) throws DocumentException, IOException, SQLException, ClassNotFoundException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        UtenteBean utente = utenteDao.findById(utenteID);

        Optional<UtenteBean> maybeValutatore = utenteDao.findAll().stream()
                .filter(user -> user.getRuoloId() == 2)
                .filter(user -> user.getValutatoreId() == utenteID)
                .findFirst();

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.setNonStrokingColor(Color.BLACK);
        contentStream.setFont(PDType1Font.COURIER, 12);
        contentStream.newLine();
        contentStream.showText(Year.now().getValue() + " Valutazione professionale di " + utente.getNome() + " " + utente.getCognome());
        contentStream.endText();
        contentStream.close();

        document.save("valutazioneProfessionale"+ utente.getNome() +"_"+ utente.getCognome() +".pdf");
        document.close();
    }
}
