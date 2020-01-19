package br.com.infnet.java.projeto_de_bloco.model;

import br.com.infnet.java.projeto_de_bloco.exception.RecursoNaoEncontradoException;

public class Saque extends Transacao{

	public Saque(Conta conta, double valor) throws RecursoNaoEncontradoException {
		super(conta, valor);
	}

	@Override
	public String executa() {
		if (this.getConta().sacar(this.getValor())) {
			String saldoFormatado = mostraMensagemFormatadaParaDolar(this.getConta().getSaldo());
			return "Saque efetuado com sucesso.\nO novo saldo da conta é: " + saldoFormatado;
		} else {
			return "Saldo insuficiente para o saque.";
		}
		
	}

}
