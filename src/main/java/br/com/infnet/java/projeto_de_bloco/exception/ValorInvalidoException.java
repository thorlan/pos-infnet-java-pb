package br.com.infnet.java.projeto_de_bloco.exception;

/**
 * Caso os valores de saque ou depósito passados pelo usuário sejam menores ou iguais a 
 * zero esta exceção será lançada
 * @author thiago
 *
 */
public class ValorInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValorInvalidoException(String msg) {
		super(msg);
	}
}
