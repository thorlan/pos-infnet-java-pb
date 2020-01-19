package br.com.infnet.java.projeto_de_bloco.model;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author thiago
 * Representa o teclado de um caixa automático.
 * Usada para consumir as entradas de um usuário em um caixa automático
 */
public class Teclado {
	
	Scanner input;
	
	public Teclado() {
		input = new Scanner(System.in);
	}
	
	/**
	 * Recupera a entrada do usuário, caso o mesmo insira algo 
	 * que não seja um inteiro, uma mensagem de erro é exibida
	 * @return a entrada do usuário.
	 */
	public int getUserInput() {
		
		try {
			return input.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Somente números são aceitos neste campo.");
			return 0;
		}
	}
	
	public void closeInput() {
		input.close();
	}
}
