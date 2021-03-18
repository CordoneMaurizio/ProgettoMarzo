/**
 * 
 */
package univpm.com.oceaniaticket.service;

import univpm.com.oceaniaticket.TicketMasterAPI.API;
import univpm.com.oceaniaticket.model.Evento;
import univpm.com.oceaniaticket.utility.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

/**
 * 
 * gestisce le operazioni principali e formatta la risposta da restituire al controller
 * 
 * @author <a> Cordone Maurizio </a>
 *
 */
@Service
public class EventService {
	
	/**
	 * 
	 * aziona il processo di richiesta e filtraggio dei dati da API esterna
	 * ritorna un oggetto di tipo List che stampato come JSONObject visualizza i parametri degli oggetti contenuti nella lista
	 * 
	 * @param inBody testo in formato JSON per il filtraggio della risposta
	 * @return listaEventi lista contente oggetti di tipo Evento
	 */
	public List<Evento> dati(Map<String,Object> inBody) {		
		API api = new API(inBody);
		List<Evento> listaEventi = new ArrayList<Evento>();
		
		JSONObject rispostaAPI = api.call();
		APIParser parser = new APIParser(rispostaAPI);
		JSONArray listaEventiAPI = parser.getEvents();
		
		for(int i = 0; i < listaEventiAPI.size(); i++){
			Evento evento = new Evento((JSONObject)listaEventiAPI.get(i));
			evento.setAll();
			listaEventi.add(evento);		
		}		
		return listaEventi;		
	}
	
	/**
	 * 
	 * utilizza la risposta del metodo dati() per calcolare statistiche sugli eventi
	 * 
	 * @param inBody testo in formato JSON per il filtraggio della risposta
	 * @return mappaStatistiche mappa conteneti le varie statistiche organizzate per tipo
	 */
	public Map<String,Object> statistiche(Map<String,Object> inBody) {
		List<Evento> listaEventi = dati(inBody);
		Map<String,Object> mappaStatistiche = new HashMap<String,Object>();
		StatParser parser = new StatParser(listaEventi);
		mappaStatistiche.put("statistiche per stato", parser.perStato());
		mappaStatistiche.put("statistiche globali", parser.globali());
		return mappaStatistiche;
	}
	
	/**
	 * 
	 * @param inBody testo in formato JSON per il filtraggio della risposta
	 * @return url utilizzato per la chiamata a API esterna
	 */
	public String readBody(Map<String,Object> inBody) {
		API api = new API(inBody);
		return api.getUrlString();			
	}
		
}

