/**
 * 
 */
package univpm.com.oceaniaticket.model;

import java.util.HashMap;
import java.util.Map;
/**
 * memorizza e opera sui contatori relativi alle statistiche del singolo stato
 * 
 * @author <a> Cordone Maurizio </a>
 *
 */
public class StatStato {
	
	public int totale = 1;
	public Map<String,Integer> perGenere = new HashMap<String,Integer>();
	
	/**
	 * incrementa di uno la quantità di eventi totali dello stato
	 */
	public void incrTotale() {
		this.totale = totale + 1;
	}
	
	/**
	 * aggiunge o incrementa la quantità di eventi reativi ad un dato genere
	 * 
	 * @param genere
	 */
	public void incrGenere ( String genere) {
		if(perGenere.containsKey(genere)) {
			int count = perGenere.get(genere);
			perGenere.put(genere, count+1);
		} else {
			perGenere.put(genere, 1);
		}		
	}
}
