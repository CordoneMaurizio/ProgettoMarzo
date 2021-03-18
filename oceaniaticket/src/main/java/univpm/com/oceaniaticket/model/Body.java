/**
 * 
 */
package univpm.com.oceaniaticket.model;

import java.util.Map;

import univpm.com.oceaniaticket.utility.BodyParser;

/**
 * memorizza informazioni ricavate dal body in ingresso
 * 
 * @author <a> Cordone Maurizio </a>
 *
 */
public class Body implements model{
	
	private String stati; //codici
	private String generi;
	private String source;
	private String inizio; //yyyy-MM-ddTHH:mm:00
	private String fine;
	private BodyParser parser;
	
	
	/**
	 * costruttore
	 * inizializza un oggetto di tipo BodyParser
	 * 
	 * @param body testo in formato JSON per il filtraggio della risposta
	 */
	public Body(Map<String,Object> body) {
		this.parser = new BodyParser(body);
		setAll();
	}
	
	/**
	 * chiama i metodi dell oggetto BodyParser e memorizza le riposte
	 */
	public void setAll() {
		this.stati = parser.getStati();
		this.generi = parser.getGeneri();
		this.source = parser.getSource();
		this.inizio = parser.getInizio();
		this.fine = parser.getFine();	
	}
	
	public String getStato() {
		return stati;
	}

	public String getGenere() {
		return generi;
	}

	public String getSource() {
		return source;
	}

	public String getInizio() {
		return inizio;
	}

	public String getFine() {
		return fine;
	}

}
