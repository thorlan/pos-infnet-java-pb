package br.com.infnet.java.projeto_de_bloco.model;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * 
 * @author thiago Representa uma transação genérica em um ATM (depósito, saque,
 *         consulta de saldo) Contem dois atributos, um é o valor da transação
 *         (double valor) e o outro é o número da conta de destino (int
 *         numeroDaConta)
 * 
 */
public abstract class Transacao {

	private double valor;
	private Conta conta;

	public Transacao(Conta conta, double valor) {
		this.conta = conta;
		this.valor = valor;
	}
	
	public Transacao(Conta conta) {
		this.conta = conta;
	}

	public double getValor() {
		return valor;
	}

	public Conta getConta() {
		return this.conta;
	}

	protected String mostraMensagemFormatadaParaDolar(double valor) {
		Locale locale = new Locale("en", "US");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		return formatter.format(valor);
	}

	public abstract String executa();
}
