package br.com.infnet.java.projeto_de_bloco.dao;

import java.util.List;
import java.util.Optional;

import br.com.infnet.java.projeto_de_bloco.exception.RecursoNaoEncontradoException;
import br.com.infnet.java.projeto_de_bloco.model.Conta;

/**
 * 
 * @author thiago
 * Esta classe representa o base de dados do banco, com informações sobre todas as contas
 */
public class BancoDB {
	
	private static int id;
	private static BancoDB bancoSingleton;
	private List<Conta> contas;
	private Persistencia persistencia;
	
	private BancoDB() {
		persistencia = new Persistencia();
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
	
	public void persiste() {
		try {
			persistencia.persist(contas);
		} catch (RecursoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Popula a base de dados com as contas existentes em Account.txt
	 */
	private void populaContas() {
		
		try {
			this.contas = persistencia.getContas();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (RecursoNaoEncontradoException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addConta(Conta conta) {
		id = this.contas.size() + 1;
		conta.setNumero(id);
		this.contas.add(conta);
		
	}
	
	public List<Conta> getContas() {
		return this.contas;
	}

}
