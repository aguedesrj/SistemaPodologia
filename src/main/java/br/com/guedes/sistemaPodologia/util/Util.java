package br.com.guedes.sistemaPodologia.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe com m?todo staticos uteis.
 * 
 * @author Guedes
 *
 */
public class Util {
	
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT_DATA_HORA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT_DATA = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Obter a data atual no formato dd/MM/yyyy
	 * 
	 * @return String
	 */
	public static String obterDataAtual(final SimpleDateFormat format) {
		return format.format(new Date());
	}	
	
	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor String
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(String valor) {
		return (valor != null && !"".equals(valor.trim()));
	}
	
	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor Long
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(Long valor) {
		return (valor != null && !"".equals(valor) && valor == 0);
	}	
	
	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor Long
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(Integer valor) {
		return (valor != null && !"".equals(valor) && valor == 0);
	}
	
	/**
	 * Campo foi preenchido.
	 * 
	 * @param valor Long
	 * @return boolean
	 */
	public static boolean isCampoPreenchido(Double valor) {
		return (valor != null && !"".equals(valor) && valor == 0);
	}	
	
	/**
	 * Formatar data no formato "dd/mm/yyyy 00:00:00"
	 * 
	 * @param data Calendar
	 * @return String
	 */
	public static String converterCalendarParaString(final Calendar data, final SimpleDateFormat format) {
		if (data != null) {
			return format.format(data.getTime());
		}
		return "";
	}	
	
	/**
	 * 
	 * @param data
	 * @param format
	 * @return
	 */
	public static String converterDateParaString(final Date data, final SimpleDateFormat format) {
		if (data != null) {
			return format.format(data);
		}
		return "";
	}	
	
	/**
	 * Formatar data no formato "yyyy-MM-dd"
	 * 
	 * @param data String
	 * @return String
	 */
	public static String formatarDataString(String data) {
		return data.substring(6, 10) + "-" + data.substring(3, 5) + "-" + data.substring(0, 2);
	}
	
	/**
	 * Converter data tipo String para data tipo Calendar.
	 * 
	 * @param valorData String
	 * @return Calendar
	 * @throws Exception
	 */
	public static Calendar converterStringParaCalendar(final String valorData) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(SIMPLE_DATE_FORMAT_DATA_HORA.parse(valorData)); 
		return calendar;
	}
	
	/**
	 * Converter decimal String em BigDecimal.
	 * @param valorDecimal String
	 * @return BigDecimal
	 */
	public static BigDecimal converterDecimalStringParaBigDecimal(String valorDecimal) {
		valorDecimal = valorDecimal.replace("R$ ", "") ;
		valorDecimal = valorDecimal.replace(".", "") ;
		return new BigDecimal(valorDecimal.replace(",", "."));
	}
	
	/**
	 * Converter BigDecimal para String.
	 * @param valor BigDecimal
	 * @return String
	 */
	public static String converterBigDecimalParaStringDecimal(BigDecimal valor) {
		DecimalFormat decimalFormat = new DecimalFormat("R$ #,##0.00"); 
		return decimalFormat.format(valor);
	}	
}
