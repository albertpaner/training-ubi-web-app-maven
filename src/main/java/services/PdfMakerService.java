package services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.dao.SezioneDao;
import model.dao.UtenteDao;
import model.dao.ValutazioneDao;
import model.bean.SezioneBean;
import model.bean.UtenteBean;
import model.bean.ValutazioneBean;

import org.apache.pdfbox.pdmodel.font.PDFont;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

public class PdfMakerService extends UtenteService{
	
	public PdfMakerService(UtenteDao uDao) {
		super(uDao);
	}
	
	static final Color DARK_GREEN = new Color(37, 137, 0);
	static final int NEW_LINE = 20;
	static final int SEZ_HEIGTH = 30;
	static final int SEZ_LENGTH = 500;
	static final int SEZ_TITLE_SIZE = 20;
	static final int TEXT_SIZE = 12;
	int height = 750;
	
	
	public int textLine() {
		return height-=50;
	}
	
	public void CreatePdf (String email) throws IOException, ClassNotFoundException, SQLException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");  
		String year = dtf.format(LocalDateTime.now());
		
		Optional<UtenteBean> uBean = findByEmail(email);
		UtenteBean utenteFound = uBean.get();
		
		SezioneDao sezDao = new SezioneDao();
		List<SezioneBean> listSezBean = sezDao.findAll().stream()
				.filter(sez -> !sez.getFlgDel())
				.toList();
		
		SezioneBean sezBean = listSezBean.get(0);
		
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		//intestazione
		contentStream.setFont(PDType1Font.TIMES_ROMAN, TEXT_SIZE);
		contentStream.beginText();
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(NEW_LINE, height);  
		contentStream.showText(year+" - Valutazione Professionale (a) per "+utenteFound.getNome()+" "+utenteFound.getCognome());
		contentStream.endText();
		//riquadro verde di sezione
        contentStream.addRect(NEW_LINE, textLine(), SEZ_LENGTH, SEZ_HEIGTH);
		contentStream.setNonStrokingColor(DARK_GREEN);
        contentStream.fill();
		contentStream.setFont(PDType1Font.TIMES_ROMAN, SEZ_TITLE_SIZE);
		contentStream.beginText();
		contentStream.setNonStrokingColor(Color.WHITE);
		contentStream.newLineAtOffset(NEW_LINE, (height+(SEZ_HEIGTH-SEZ_TITLE_SIZE)/2));  
		contentStream.showText(sezBean.getTitoloSez());
		contentStream.endText();
		//informazioni generali
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentStream.beginText();
		contentStream.setNonStrokingColor(Color.BLACK);
		contentStream.newLineAtOffset(NEW_LINE, textLine());  
		contentStream.showText("Nome: "+utenteFound.getNome().toUpperCase());
		contentStream.newLineAtOffset(NEW_LINE, textLine());  
		contentStream.showText("Nome: "+utenteFound.getNome().toUpperCase());
		contentStream.endText();
        
		contentStream.close();

		try {
			document.save("SchedaValutazione.pdf");
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
