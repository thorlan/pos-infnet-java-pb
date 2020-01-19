package br.com.infnet.java.projeto_de_bloco.model;

import java.util.Scanner;

/**
 * 
 * @author thiago
 * Representa o teclado de um caixa automático.
 * Usada para consumir as entradas de um usuário em um caixa automático
 */
public class Teclado {
	
	Scanner input = new Scanner(System.in);
	/**
	 * Recupera a entrada do usuário, caso o mesmo insira algo 
	 * que não seja um inteiro, uma mensagem de erro é exibida
	 * @return a entrada do usuário.
	 */
	public Integer getUserInput() {
		return input.nextInt();
	}

	public void closeInput() {
		input.close();
	}
	
	
}
