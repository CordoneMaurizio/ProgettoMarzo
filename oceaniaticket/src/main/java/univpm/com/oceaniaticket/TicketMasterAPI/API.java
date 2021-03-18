/**
 * 
 */
package univpm.com.oceaniaticket.TicketMasterAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.URLConnection;
import java.util.Map;
import java.net.URL;
import org.json.simple.parser.ParseException;

import univpm.com.oceaniaticket.model.Body;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
/**
 * 
 * invia la richiesta a API esterna e ritorna la risposta
 * 
 * @author <a> Cordone Maurizio </a>
 *
 */
public class API {
	
	private String baseURL ="https://app.ticketmaster.com/discovery/v2/events.json?&countryCode=AU&size=200";
	private final String APIkey = "M3LyhpAl8jvIjH1k9t7VdKojV4WDyYAA";
	private String urlString;
	private Body body;
	
	/**
	 * costruttore
	 * istanzia un oggetto di tipo Body
	 * 
	 * @param bodyMap testo in formato JSON per il filtraggio della risposta
	 */
	public API(Map<String,Object> bodyMap) {
		this.body = new Body(bodyMap);
		writeURL();
	}
	
	public String getUrlString() {
		return urlString;
	}
	
	/**
	 * 
	 * utilizza metodi dell oggetto di tipo Body per scrivere l'url da utilizzare
	 * 
	 */
	public void writeURL() {
		String url = this.baseURL + "&apikey=" + this.APIkey;
		if(this.body.getGenere() != null) {
			url += "&classificationName=" + this.body.getGenere();
		}
		if(this.body.getStato() != null) {
			url += "&stateCode=" + this.body.getStato();
		}
		if(this.body.getSource() != null) {
			url += "&source=" + this.body.getSource();
		}
		if(this.body.getInizio() != null || this.body.getFine() != null) {
			url += "&localStartEndDateTime=";
			if(this.body.getInizio() != null) {
				url += this.body.getInizio();
			} else { url += "*";}
			url += ",";
			if(this.body.getFine() != null) {
				url += this.body.getFine();
			} else { url += "*"; }
		}
		
		this.urlString = url;
	}
	
	/**
	 * 
	 * utilizza l'url per fare richiesta e API esterna 
	 * @return rispostaObj risposta in fomato JSON
	 */
	public JSONObject call() {
		JSONObject rispostaObj = new JSONObject();
		try {
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			String risposta = "";
			BufferedReader lettore = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			while((line = lettore.readLine()) != null) {
				risposta += line;
			}
			lettore.close();
			rispostaObj= (JSONObject)JSONValue.parseWithException(risposta);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
			return rispostaObj;	
	}				

}
