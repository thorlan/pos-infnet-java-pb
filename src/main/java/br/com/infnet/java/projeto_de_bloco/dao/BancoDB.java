package br.com.infnet.java.projeto_de_bloco.dao;

import java.util.List;
import java.util.Optional;

import br.com.infnet.java.projeto_de_bloco.exception.RecursoNaoEncontradoException;
import br.com.infnet.java.projeto_de_bloco.model.Conta;

/**
 * Classe que representa o banco de dados do sistema.
 * Uma única instância permanece em memória por execução do programa (SINGLETON)
 * 
 * @author thiago
 *
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
	
	/**
	 * Busca uma na lista de contas do sistema pelo número da mesma.
	 * @param numeroDaConta
	 * @return Conta
	 */
	public Optional<Conta> findConta(int numeroDaConta) {
		 return contas.stream().filter(c -> c.getNumero() == numeroDaConta).findFirst();
	}
	
	/**
	 * Responsável por manter o padrão SINGLETON da instância de BancoDV
	 * @return BancoDB
	 */
	public static synchronized BancoDB getInstance() {
        if (bancoSingleton == null) {
        	bancoSingleton = new BancoDB();
        }
 
        return bancoSingleton;
    }
	
	/**
	 * Percorre a lista de contas verificando a combinação de número e pin para cada uma das contas existentes.
	 * @param numeroDaConta
	 * @param pin
	 * @return boolean
	 */
	public boolean authenticateUser(int numeroDaConta, int pin) {
		return contas.stream().anyMatch(c -> verificaPinParaConta(numeroDaConta, pin, c));
	}
	
	/**
	 * Verifica combinação de numero de conta e numero de pin. Caso os valores informados 
	 * sejam iguais a de uma determinada conta, o retorno será verdadeiro.
	 * @param numeroDaConta
	 * @param pin
	 * @param c
	 * @return boolean
	 */
	private boolean verificaPinParaConta(int numeroDaConta, int pin, Conta c) {
		return (c.getNumero() == numeroDaConta) && (c.getPin() == pin);
	}
	
	/**
	 * Método responsável por salvar a lista de contas (List<Conta<>) no arquivo Account.txt
	 */
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
	
	/**
	 * Salva uma nova conta no arquivo Account.txt.
	 * @param conta
	 */
	public void addConta(Conta conta) {
		id = this.contas.size() + 1;
		conta.setNumero(id);
		this.contas.add(conta);
		
	}
	
	/**
	 * Retorna a lista de todas as contas salvas no sistema.
	 * @return List<Conta>
	 */
	public List<Conta> getContas() {
		return this.contas;
	}

}
