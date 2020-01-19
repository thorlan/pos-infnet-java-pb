package br.com.infnet.java.projeto_de_bloco.model;

public class Deposito extends Transacao {

	public Deposito(Conta conta, double valor) {
		super(conta, valor);
	}

	@Override
	public String executa() {
		this.getConta().depositar(this.getValor());
		String saldoFormatado = mostraMensagemFormatadaParaDolar(this.getConta().getSaldo());
		return "Depósito de: " + this.mostraMensagemFormatadaParaDolar(getValor()) +
				" efetuado na conta: " + this.getConta().getNumero();
	}

}
