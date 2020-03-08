package br.com.infnet.java.projeto_de_bloco.model;

import java.text.NumberFormat;
import java.util.Locale;

import br.com.infnet.java.projeto_de_bloco.exception.ValorInvalidoException;

/**
 * Representa uma transação genérica do ATM.
 * @author thiago
 *
 */
public abstract class Transacao {

	private double valor;
	private Conta conta;

	public Transacao(Conta conta, double valor) throws ValorInvalidoException {
		checaValor(valor);
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
	
	/**
	 * Somente são aceitos valores > 0 para as transações.
	 * @param valor
	 * @throws ValorInvalidoException
	 */
	private void checaValor(double valor) throws ValorInvalidoException {
		if (valor < 0) {
			throw new ValorInvalidoException("Somente valores maiores que zero são aceitos");
		}
	}

	/**
	 * Mostra mensagem amigável com valores de moeda formatados em en-US
	 * @param valor
	 * @return String
	 */
	protected String mostraMensagemFormatadaParaDolar(double valor) {
		Locale locale = new Locale("en", "US");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		return formatter.format(valor);
	}

	/**
	 * As transações devem ter as suas próprias implementações.
	 * @return String
	 */
	public abstract String executa();
}
