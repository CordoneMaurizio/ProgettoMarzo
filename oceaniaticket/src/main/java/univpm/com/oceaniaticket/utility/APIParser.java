/**
 * 
 */
package univpm.com.oceaniaticket.utility;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * legge e divide in JSONObject la risposta ricevuta da API esterna
 * 
 * @author <a> Cordone Maurizio </a> 
 *
 */
public class APIParser extends Parser {
	
	/**
	 * costruttore
	 * 
	 * @param rispostaAPI JSONObject
	 */
	public APIParser(JSONObject rispostaAPI) {
		super(rispostaAPI);
	}
	
	/**
	 * ritorna un JSONArray contenente gli eventi ricevuti
	 * 
	 * @return events JSONArray
	 */
	public JSONArray getEvents(){
		JSONArray events = new JSONArray();
		JSONObject embedded = (JSONObject)obj.get("_embedded");
		events = (JSONArray)embedded.get("events");
		return events;
	}

}
