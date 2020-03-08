package br.com.infnet.java.projeto_de_bloco.model;

/**
 * Representa o dispensador de cédulas do banco.
 * @author thiago
 *
 */
public class DispensadorDeCedulas {

	private static final int INITIAL_COUNT = 500;
	private static int count = 0;

	/**
	 * Verifica se o montante de cédulas é suficiente para determinado valor.
	 * @param amount
	 * @return Boolean
	 */
	private boolean isSufficientCashAvailable(int amount) {

		if ((amount / 20 + count) > INITIAL_COUNT) {
			return false;
		}
		return true;
	}

	/**
	 * Recalcula o montante de cédulas após uma operação de saque.
	 * @param amount
	 * @return Boolean
	 */
	public boolean dispenseCash(int amount) {
		if (isSufficientCashAvailable(amount)) {
			count += amount;
			return true;
		}
		return false;
	}

}
