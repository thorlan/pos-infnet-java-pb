package br.com.infnet.java.projeto_de_bloco.model;

import br.com.infnet.java.projeto_de_bloco.dao.BancoDB;
import br.com.infnet.java.projeto_de_bloco.exception.ValorInvalidoException;

public class Saque extends Transacao{

	private BancoDB bancoDb;
	
	public Saque(Conta conta, double valor, BancoDB bancoDb) throws ValorInvalidoException  {
		super(conta, valor);
		this.bancoDb = bancoDb;
	}

	@Override
	public String executa()  {
		
		if (this.getConta().sacar(this.getValor())) {
			String saldoFormatado = mostraMensagemFormatadaParaDolar(this.getConta().getSaldo());
			bancoDb.persiste();
			return "Saque efetuado com sucesso.\nO novo saldo da conta é: " + saldoFormatado;
		} else {
			return "Saldo insuficiente para o saque.";
		}
		
	}

}
