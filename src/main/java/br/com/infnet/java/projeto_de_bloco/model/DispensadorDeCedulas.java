package br.com.infnet.java.projeto_de_bloco.model;

/**
 * 
 * @author thiago
 * Representa a o dispensador de cédulas do ATM
 * O ATM é abastecido com 500 notas de 20.
 */
public class DispensadorDeCedulas {

	private static final int INITIAL_COUNT = 500;
	private static int count = 0;
	
	/**
	 * O ATM tem 500 cédulas de 20, para saber se o montante é suficiente precisamos
	 * dividir o desejado por 20, somar ao contador e verificar se o resultado é
	 * maior que o contador inicial.
	 */
	public boolean isSufficientCashAvailable(int amount) {

		if ((amount/20 + count) > INITIAL_COUNT) {
			return false;
		}
		return true;
	}
	
	public void dispenseCash(int amount) {
		if (isSufficientCashAvailable(amount)) {
			count += amount;
		}
	}
	
}
