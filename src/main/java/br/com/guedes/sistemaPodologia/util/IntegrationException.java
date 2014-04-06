package br.com.guedes.sistemaPodologia.util;

/** 
 * Excess�o gerada pelas classes de acesso a dados.
 * @version 1.0 
 */
public class IntegrationException extends Exception {

	private static final long serialVersionUID = 2042881432905715188L;

	/**
	 * Construtor
	 * @since 17/03/2008 
	 * @param mensage - String - Mensagem a ser exibida 
	 * @param cause - Throwable - Exce��o ocorrida
	 */
	public IntegrationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 */
	public IntegrationException(String message) {
		
		super(message);
	}	
	
	/**
	 * 
	 * @param cause
	 */
	public IntegrationException(Throwable cause) {
		
		super(cause);
	}	
}
