package br.com.infnet.java.projeto_de_bloco;

import br.com.infnet.java.projeto_de_bloco.dao.BancoDB;
import br.com.infnet.java.projeto_de_bloco.model.Conta;

/**
 * Classe de utilidades para popular o arquivo Account.txt com duas contas.
 * @author thiago
 *
 */
public class PopulaContas {

	public static void main(String[] args) {
		BancoDB bd = BancoDB.getInstance();
		
		Conta conta = new Conta(1, 300, 123, "Maria");
		Conta conta2 = new Conta(2, 600, 456, "João");
		
	    bd.addConta(conta);
		bd.addConta(conta2);
		bd.persiste();
		
	}
}
