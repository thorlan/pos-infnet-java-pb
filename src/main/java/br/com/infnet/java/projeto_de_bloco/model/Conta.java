package br.com.infnet.java.projeto_de_bloco.model;

import java.io.Serializable;

/**
 * Representa uma conta de um cliente com suas informações e operações.
 * @author thiago
 *
 */
public class Conta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private int numero;
	private double saldo;
	private int pin;
	
	public Conta(int numero, double saldo, int pin, String nome) {
		this.numero = numero;
		this.saldo = saldo;
		this.pin = pin;
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public int getPin() {
		return pin;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	

	/**
	 * Efetua um saque na conta se e somente se o valor passado não for maior que o saldo.
	 * @param valor
	 * @return Boolean
	 */
	public boolean sacar(double valor) {
		if (valor > this.saldo) {
			return false;
		} else {
			this.saldo -= valor;
			return true;
		}
	}
	
	/**
	 * Efetua o depósito na conta.
	 * @param valor
	 */
	public void depositar(double valor) {
		this.saldo += valor;
	}
	
}
