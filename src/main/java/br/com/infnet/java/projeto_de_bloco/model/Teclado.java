package br.com.infnet.java.projeto_de_bloco.model;

import java.util.Scanner;

/**
 * Representa o teclado do ATM, usadda para consumir as entradas do usuário.
 * @author thiago
 *
 */
public class Teclado {
	
	Scanner input = new Scanner(System.in);
	
	/**
	 * Recupera a entrada do usuário. Caso o valor passado não seja um inteiro, um erro é lançado.
	 * @return
	 */
	public Integer getUserInput() {
		return input.nextInt();
	}

	public void closeInput() {
		input.close();
	}
	
	
}
