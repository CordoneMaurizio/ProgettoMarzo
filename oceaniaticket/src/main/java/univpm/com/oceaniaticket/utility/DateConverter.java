/**
 * 
 */
package univpm.com.oceaniaticket.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * 
 * @author <a> Cordone Maurizio </a>
 *
 */
public class DateConverter {
	
	/**
	 * converte una data in formato unix in una stringa utilizzata per la compilazione dell'url
	 * @param date formato unix
	 * @return uscita stringa contenente una data formattata
	 */
	public String unixToDate(Long date) {
		Date unixDate = new Date(date*1000L); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String uscita = sdf.format(unixDate);
		uscita = uscita.replace(' ', 'T');
		return uscita;
		
	}
	
}
