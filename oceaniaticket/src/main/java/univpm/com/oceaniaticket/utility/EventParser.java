/**
 * 
 */
package univpm.com.oceaniaticket.utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")

/**
 * legge e formatta le informazioni relative ad un singolo evento
 * 
 * @author <a> Cordone Maurizio </a>
 *
 */
public class EventParser extends Parser{
	
	/**
	 * costruttore
	 * 
	 * @param eventoObj JSONObject contenete i dati relativi ad un singolo evento
	 */
	public EventParser (JSONObject eventoObj) {
		super(eventoObj);
	}	
	
	/**
	 * @return location parte del JSONObject da cui ricavare altre informazioni
	 */
	public JSONObject getLocation() {
		JSONObject embedded = (JSONObject)obj.get("_embedded");
		JSONArray venues = (JSONArray)embedded.get("venues");
		JSONObject location = (JSONObject)venues.get(0);
		return location;
	}
	
	/**
	 * @return nome stringa contenente il nome dell'evento
	 */
	public String getNome() {
		String nome = (String)obj.get("name");
		return nome;
	}
	
	/**
	 * @param location parte del JSONObject da cui ricavare altre informazioni
	 * @return locale JSONObject contenente nome e indirizzo del locale che ospita l'evento
	 */
	public JSONObject getLocale(JSONObject location) {
		JSONObject locale = new JSONObject();
		String nomeLocale = (String)location.get("name");
		locale.put("nome", nomeLocale);
		if(location.get("address") != null) {
			JSONObject addressObj = (JSONObject)location.get("address");
			String address = (String)addressObj.get("line1");
			locale.put("indirizzo", address);
		}		
		return locale;
	}
	
	/** 
	 * @param location parte del JSONObject da cui ricavare altre informazioni
	 * @return state 
	 */
	public String getStato(JSONObject location) {
		JSONObject stateObj = (JSONObject)location.get("state");
		String state = (String)stateObj.get("name");
		return state;
	}
	
	/**
	 * @param location parte del JSONObject da cui ricavare altre informazioni
	 * @return city città che ospita l'evento
	 */
	public String getCitta(JSONObject location) {
		JSONObject cityObj = (JSONObject)location.get("city");
		String city = (String)cityObj.get("name");
		return city;	
	}

	/**
	 * @return data data e ora in cui inizia l'evento
	 */
	public String getData() {
	    JSONObject dates = (JSONObject)obj.get("dates");
	    JSONObject start = (JSONObject)dates.get("start");	    
	    String startDate = (String)start.get("localDate");
	    String startTime = (String)start.get("localTime");
	 	String data = startDate + " " + startTime;
		return data;
	}
	
	/**
	 * @return genre genere di classificazione dell' evento
	 */
	public String getGenere() {
		JSONArray classification =(JSONArray)obj.get("classifications");
		JSONObject classificationObj = (JSONObject)classification.get(0);
		JSONObject segment = (JSONObject)classificationObj.get("segment");
		String genre = (String)segment.get("name");
		return genre;
	}
	
	
}
