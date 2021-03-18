/**
 * 
 */
package univpm.com.oceaniaticket.utility;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
/**
 * legge e formatta informazioni ricavate dal body in ingresso
 * 
 * @author <a> Cordone Maurizio </a>
 *
 */
public class BodyParser {
	
	private Map<String,Object> body;
	
	/**
	 * costruttore
	 * 
	 * @param body testo in formato JSON per il filtraggio della risposta
	 */
	public BodyParser(Map<String,Object> body) {
		this.body = body;	
	}
	
	/**
	 * legge e formatta il campo stati 
	 * 
	 * @return stato stringa di uno o più stati per la composizione dell'url
	 */
	public String getStati() {
		String stato = "";
		boolean check = false;
		if(this.body.get("stati") != null) {
			String statoBody = (String)this.body.get("stati");
			if(statoBody.contains("nsw")||statoBody.contains("new south wales")) {
				stato += "nsw,";
				check = true;
			}
			if(statoBody.contains("qld")||statoBody.contains("queensland")) {
				stato += "qld,";
				check = true;
			}
			if(statoBody.contains("sa")||statoBody.contains("south australia")) {
				stato += "sa,";
				check = true;
				}
			if(statoBody.contains("tas")||statoBody.contains("tasmania")) {
				stato += "tas,";
				check = true;
			}
			if(statoBody.contains("vic")||statoBody.contains("victoria")) {
				stato += "vic,";
				check = true;
			}
			if(statoBody.contains("wa")||statoBody.contains("western australia")) {
				stato += "wa,";
				check = true;
			}
			if(!check) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"campo -stati- non corretto");
			}
		}		
			return stato;
		}
	
	/**
	 * legge e formatta il campo source
	 * 
	 * @return source stringa di uno o più source per la composizione dell'url
	 */
	public String getSource() {
	    String source = (String)this.body.get("source");
	    return source;
	}
	
	/**
	 * legge e formatta il campo generi
	 * 
	 * @return generi stringa di uno o più generi per la composizione dell'url
	 */
	public String getGeneri() {
		String generi = (String)this.body.get("generi");
		return generi;
	}
	
	/**
	 * legge la data attuale e la converte in stringa
	 * 
	 * @return inizio stringa formattata per la composizione dell'url
	 */
	public String getInizio() {
		DateConverter converter = new DateConverter();
		Long unixDate = System.currentTimeMillis()/1000L;
		String inizio = converter.unixToDate(unixDate);
		return inizio;
	}
	
	/**
	 * legge la data attuale e calcola la data alla fine di un dato arco temporale
	 * 
	 * @return fine stringa formattata per la composizione dell'url
	 */
	public String getFine() {
		String fine = "";
		if(this.body.get("range temporale") != null) {
			String finestra = (String)this.body.get("range temporale");
			DateConverter converter = new DateConverter();
			Long unixDate = System.currentTimeMillis()/1000L;
			switch(finestra) {
			case "3 mesi": unixDate += 2629743L*3L; break;
			case "6 mesi": unixDate += 2629743L*6L; break;
			case "anno corrente": unixDate += 2629743L*12L; break;
			default : throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"campo -range temporale- non corretto");
			}
			fine = converter.unixToDate(unixDate);
		} 		
		return fine;
	}
	

}
