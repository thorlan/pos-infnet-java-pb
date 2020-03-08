package br.com.infnet.java.projeto_de_bloco.model;

/**
 * Representa a tela(menu) do ATM. Utilizada para mostrar mensagens aos usuários.
 * @author thiago
 *
 */
public class Tela {

	/**
	 * Imprime a mensagem na tela para ser visualizada pelo usuário.
	 * @param msg
	 */
	public void mostraMensagem(String msg) {
		System.out.println(msg);
	}

	/**
	 * Mostra o menu do ATM para o usuário logado.
	 */
	public void showMenu() {
		System.out.println("-----------ATM-----------------");
		System.out.println("1: Consulta de saldo");
		System.out.println("2: Depósito");
		System.out.println("3: Saque");
		System.out.println("4: Sair");
	}
	/**
	 * Mostra o menu do ATM para usuários não logados.
	 */
	public void showMenuPadrao() {
		System.out.println("-----------ATM-----------------");
		System.out.println("1: Logar no sistema");
		System.out.println("2: Depósito");
		
	}
	
	public void mostraMensagemDeBoasVindas(Conta conta) {
		System.out.println("Usuário Logado: " + conta.getNome() + " conta: " + conta.getNumero());
	}

	public void delimitador() {
		System.out.println("--------------------------------");
		System.out.println("\n\n");
	}

}
