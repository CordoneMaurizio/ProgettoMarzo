/**
 * 
 */
package univpm.com.oceaniaticket.utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import univpm.com.oceaniaticket.model.Evento;
import univpm.com.oceaniaticket.model.StatStato;

/**
 * legge e formatta i dati utili per il calcolo delle statitiche
 * 
 * @author <a> Cordone Maurizio </a>
 *
 */
public class StatParser {
	
	List<Evento> listaEventi;
	
	int StatoMax = Integer.MIN_VALUE;
	int StatoMin = Integer.MAX_VALUE;
	Map<String,StatStato> mappaStat = new HashMap<String,StatStato>();
	
	/**
	 * costruttore
	 * 
	 * @param listaEventi lista di oggetti Evento
	 */
	public StatParser(List<Evento> listaEventi) {
		this.listaEventi = listaEventi;
	}
	
	/**
	 * legge e aggiorna una mappa contenete le statistiche relative ad ogni stato
	 * 
	 * @return mappaStat
	 */
	public Map<String,StatStato> perStato() {
		for(Evento e : listaEventi) {
			if(mappaStat.containsKey(e.getStato())) {
				(mappaStat.get(e.getStato())).incrTotale();
				(mappaStat.get(e.getStato())).incrGenere(e.getGenere());				
			} else {
				mappaStat.put(e.getStato(), new StatStato());
				(mappaStat.get(e.getStato())).incrGenere(e.getGenere());				
			}
		}
		return mappaStat;
	}
	
	/**
	 * aggiorna i dati relativi alle statistiche globali
	 * 
	 * @return statGlobali
	 */
	public Map<String,Integer> globali(){
		String nomeMax = "";
		String nomeMin = "";
		Map<String,Integer> statGlobali = new HashMap<String,Integer>();
			for (Map.Entry<String,StatStato> entry : mappaStat.entrySet()) {
				if(entry.getValue().totale < this.StatoMin) {
					this.StatoMin = entry.getValue().totale;
					nomeMin = entry.getKey();
				}
				if(entry.getValue().totale > this.StatoMax) {
					this.StatoMax = entry.getValue().totale;
					nomeMax = entry.getKey();
				}			
			}
		statGlobali.put("stato con più eventi: " + nomeMax, this.StatoMax);
		statGlobali.put("stato con meno eventi: " + nomeMin , this.StatoMin);
		statGlobali.put("numero totale eventi", listaEventi.size());
		return statGlobali;
	}
}
