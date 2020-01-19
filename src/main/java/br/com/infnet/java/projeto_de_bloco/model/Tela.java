package br.com.infnet.java.projeto_de_bloco.model;

import java.io.IOException;

/**
 * 
 * @author thiago Representa a tela de um caixa automático. Usada para exibir as
 *         mensagens para o usuário.
 */
public class Tela {

	/**
	 * @param Uma String contendo a mensagem a ser exibida na tela
	 * @return void
	 */
	public void mostraMensagem(String msg) {
		System.out.println(msg);
	}

	/**
	 * Mostra o menu do ATM.
	 * 
	 * @return void
	 */
	public void showMenu() {
		System.out.println("-----------ATM-----------------");
		System.out.println("1: Consulta de saldo");
		System.out.println("2: Depósito");
		System.out.println("3: Saque");
		System.out.println("4: Sair");
		delimitador();
	}

	public void showMenuPadrao() {
		System.out.println("-----------ATM-----------------");
		System.out.println("1: Logar no sistema");
		System.out.println("2: Depósito");
		delimitador();
		System.out.println("\n\n");
		
	}
	
	public void mostraMensagemDeBoasVindas(Conta conta) {
		System.out.println("Usuário Logado: " + conta.getNome() + " conta: " + conta.getNumero());
	}

	public void delimitador() {
		System.out.println("--------------------------------");
	}

}
