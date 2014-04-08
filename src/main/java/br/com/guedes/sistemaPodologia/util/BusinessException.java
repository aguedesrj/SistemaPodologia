package br.com.guedes.sistemaPodologia.util;

/** 
 * Excess�o gerada pelas classes de acesso a dados.
 * @version 1.0 
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 5218287555187832593L;

	/**
	 * Construtor
	 * @since 17/03/2008 
	 * @param mensage - String - Mensagem a ser exibida 
	 * @param cause - Throwable - Exce��o ocorrida
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param message
	 */
	public BusinessException(String message) {
		
		super(message);
	}	
	
	/**
	 * 
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		
		super(cause);
	}	
}
