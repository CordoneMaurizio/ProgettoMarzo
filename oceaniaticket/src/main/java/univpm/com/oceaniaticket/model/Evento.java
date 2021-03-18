/**
 * 
 */
package univpm.com.oceaniaticket.model;

import org.json.simple.JSONObject;

import univpm.com.oceaniaticket.utility.EventParser;

/**
 * memorizza informazioni ricavate dalla risposta dell'API esterna
 * 
 * @author <a> Cordone Maurizio </a>
 *
 */
public class Evento implements model {
	
	private String nome;
	private JSONObject locale;
	private String stato;
	private String citta;
	private String data;
	private String genere;
	private EventParser parser;
	
	/**
	 * costruttore
	 * inizializza un oggetto di tipo EventParser
	 * 
	 * @param eventoObj JSONObject contenete i dati relativi ad un singolo evento
	 */
	public Evento(JSONObject eventoObj) {
		parser = new EventParser(eventoObj);
	}
	
	/**
	 * chiama i metodi dell'oggetto EventParser e memorizza le rispote
	 */
	public void setAll() {
		this.nome = parser.getNome();
		this.genere = parser.getGenere();
		this.data = parser.getData();
		this.locale = parser.getLocale(parser.getLocation());
		this.stato = parser.getStato(parser.getLocation());	
		this.citta = parser.getCitta(parser.getLocation());
	}
	
	public String getGenere() {
		return genere;
	}
	
	public String getStato() {
		return stato;
	}
	
	public String getNome() {
		return nome;
	}
	
	public JSONObject getLocale() {
		return locale;
	}
	
	public String getData() {
		return data;
	}	
	
	public String getCitta() {
		return citta;
	}
	
	
}