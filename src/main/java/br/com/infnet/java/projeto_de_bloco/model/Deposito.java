package br.com.infnet.java.projeto_de_bloco.model;

import br.com.infnet.java.projeto_de_bloco.dao.BancoDB;
import br.com.infnet.java.projeto_de_bloco.exception.ValorInvalidoException;

/**
 * Classe que efetua depósito para uma determinada conta.
 * @author thiago
 * @see Transacao
 *
 */
public class Deposito extends Transacao {

	private BancoDB bancoDb;
	
	public Deposito(Conta conta, double valor, BancoDB bancoDb) throws ValorInvalidoException  {
		super(conta, valor);
		this.bancoDb = bancoDb;
	}
	
	/**
	 * Efetua o depósito para a conta especificada e e salva os novos valores no banco.
	 */
	@Override
	public String executa() {
		
		this.getConta().depositar(this.getValor());
		bancoDb.persiste();
		return "Depósito de: " + this.mostraMensagemFormatadaParaDolar(getValor()) +
				" efetuado na conta: " + this.getConta().getNumero();
	}

}
