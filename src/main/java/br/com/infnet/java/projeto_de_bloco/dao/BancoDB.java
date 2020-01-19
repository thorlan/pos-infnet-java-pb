package br.com.infnet.java.projeto_de_bloco.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.infnet.java.projeto_de_bloco.model.Conta;

/**
 * 
 * @author thiago
 * Esta classe representa o base de dados do banco, com informações sobre todas as contas
 */
public class BancoDB {
	
	private static BancoDB bancoSingleton;
	private static List<Conta> contas;
	
	private BancoDB() {
		contas = new ArrayList<>();
		populaContas();
	}
	
	public Optional<Conta> findConta(int numeroDaConta) {
		 return contas.stream().filter(c -> c.getNumero() == numeroDaConta).findFirst();
	}
	
	public static synchronized BancoDB getInstance() {
        if (bancoSingleton == null) {
        	bancoSingleton = new BancoDB();
        }
 
        return bancoSingleton;
    }
	
	public boolean authenticateUser(int numeroDaConta, int pin) {
		return contas.stream().anyMatch(c -> verificaPinParaConta(numeroDaConta, pin, c));
	}
	
	private boolean verificaPinParaConta(int numeroDaConta, int pin, Conta c) {
		return (c.getNumero() == numeroDaConta) && (c.getPin() == pin);
	}
	
	/**
	 * Popula a base de dados com duas contas para serem trabalhadas no sistema ATM 
	 */
	private void populaContas() {
		Conta conta = new Conta(1,400,123);
		Conta contaDois = new Conta(2,800,345);
		
		contas.add(conta);
		contas.add(contaDois);
	}

}
