package br.com.infnet.java.projeto_de_bloco.model;

import br.com.infnet.java.projeto_de_bloco.dao.BancoDB;

public class Deposito extends Transacao {

	private BancoDB bancoDb;
	
	public Deposito(Conta conta, double valor, BancoDB bancoDb) {
		super(conta, valor);
		this.bancoDb = bancoDb;
	}

	@Override
	public String executa() {
		this.getConta().depositar(this.getValor());
		bancoDb.persiste();
		String saldoFormatado = mostraMensagemFormatadaParaDolar(this.getConta().getSaldo());
		return "Depósito de: " + this.mostraMensagemFormatadaParaDolar(getValor()) +
				" efetuado na conta: " + this.getConta().getNumero();
	}

}
