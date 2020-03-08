package br.com.infnet.java.projeto_de_bloco.exception;

/**
 * Caso um recurso (Conta) não seja encontrada no sistema, esta excecao é lançada.
 * @author thiago
 *
 */
public class RecursoNaoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(String msg) {
		super(msg);
	}
}
