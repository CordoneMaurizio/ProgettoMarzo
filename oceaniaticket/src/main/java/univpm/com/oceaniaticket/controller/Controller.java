/**
 * 
 */
package univpm.com.oceaniaticket.controller;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import univpm.com.oceaniaticket.service.EventService;
@SuppressWarnings("unchecked")

/**
 * gestisce le richieste relative alle rotte<br>
 * <i>/dati<br>
 * /stat<br>
 * /all</i>
 * 
 * @author <a> Cordone Maurizio </a>
 *
 */
@RestController
@RequestMapping(path = "api/oceaniaticket")
public class Controller {
	
	public final EventService eventservice;
	/**
	 * @param eventservice inizializza a runtime la classe eventservice
	 */
	@Autowired
	public Controller(EventService eventservice) {
		this.eventservice = eventservice;
	}
	
	/**
	 * 
	 * @param body testo in formato JSON
	 * @return risposta JSON contenente la lista di eventi filtrata e l'url utilizzato 
	 */
	@PostMapping("/dati")
	public JSONObject dati(@RequestBody Map<String,Object> body) {
		JSONObject risposta = new JSONObject();
		risposta.put("url", eventservice.readBody(body));
		risposta.put("eventi", eventservice.dati(body));
		return risposta;
	}
	
	/**
	 * 
	 * @param body testo in formato JSON
	 * @return risposta JSON contenente le statistiche fatte su una lista filtrata di eventi
	 */
	@PostMapping("/stat")
	public JSONObject statistiche(@RequestBody Map<String,Object> body) {
		JSONObject risposta = new JSONObject();
		risposta.put("Statistiche", eventservice.statistiche(body));
		return risposta;	
	}
	
	/**
	 * 
	 * @param body testo in formato JSON
	 * @return risposta JSON contenente dati e statistiche riguardanti una lista filtrata di eventi e l'url utilizzato
	 */
	@PostMapping("/all")
	public JSONObject all(@RequestBody Map<String,Object> body) {
		JSONObject risposta = new JSONObject();
		risposta.put("url", eventservice.readBody(body));
		risposta.put("eventi", eventservice.dati(body));
		risposta.put("Statistiche", eventservice.statistiche(body));
		return risposta;	
	}	
}
