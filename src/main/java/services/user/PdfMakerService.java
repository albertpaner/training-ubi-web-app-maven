package services.user;

import com.itextpdf.text.Font;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import model.bean.SezioneBean;
import model.bean.UtenteBean;
import model.dao.SezioneDao;
import model.dao.UtenteDao;
import services.UtenteService;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class PdfMakerService extends UtenteService {

    public PdfMakerService(UtenteDao uDao) throws SQLException, ClassNotFoundException {
        super(uDao);
    }

    static final Color DARK_GREEN = new Color(37, 137, 0);
    static final int NEW_LINE = 20;
    static final int SEZ_HEIGTH = 30;
    static final int SEZ_LENGTH = 500;
    static final int SEZ_TITLE_SIZE = 20;
    static final int TEXT_SIZE = 12;

    static final double MOLT_SPACE_TITLE = 2.5;
    static final double MOLT_SPACE_STAND = 0.8;
    int height = 750;


    public int textLine(double molt) {
        return height -= 20 * molt;
    }

    public void CreatePdf(String email) throws IOException, ClassNotFoundException, SQLException, DocumentException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        String year = dtf.format(LocalDateTime.now());

        Optional<UtenteBean> uBean = findByEmail(email);
        UtenteBean utenteFound = uBean.get();

        SezioneDao sezDao = new SezioneDao();
        List<SezioneBean> listSezBean = sezDao.findAll().stream()
                .filter(sez -> !sez.getFlgDel())
                .toList();

        SezioneBean sezBean = listSezBean.get(0);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("SchedaValutazione"+utenteFound.getUtenteId()+".pdf"));
        document.open();

        //intestazione
        Font font = FontFactory.getFont(FontFactory.HELVETICA, TEXT_SIZE, BaseColor.BLACK);
        Paragraph paragraph = new Paragraph(year + " - Valutazione Professionale (a) per " + utenteFound.getNome() + " " + utenteFound.getCognome(), font);
        document.add(paragraph);

        //riquadro verde di sezione
        Chunk chunk = new Chunk(sezBean.getTitoloSez(), FontFactory.getFont(FontFactory.COURIER, SEZ_TITLE_SIZE, BaseColor.WHITE));
        chunk.setBackground(BaseColor.GREEN);
        document.add(new Paragraph(chunk));

        //informazioni generali
        font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        document.add(new Paragraph("Nome: " + utenteFound.getNome().toUpperCase(), font));
        document.add(new Paragraph("Cognome: " + utenteFound.getCognome().toUpperCase(), font));
        document.add(new Paragraph("Responsabile: "));

        document.close();
    }

}
