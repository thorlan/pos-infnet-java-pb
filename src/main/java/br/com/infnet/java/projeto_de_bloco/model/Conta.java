package br.com.infnet.java.projeto_de_bloco.model;

/**
 * 
 * @author thiago
 * Representa uma conta de um cliente do banco
 * Contem três atributos: 
 * 	o saldo da conta (double saldo) 
 * 	número da conta, que é o identificador da mesma (int numero)
 *  pin do cliente, que serve para autenticar o mesmo (int pin)
 * 
 */
public class Conta {

	private int numero;
	private double saldo;
	private int pin;
	
	public Conta(int numero, double saldo, int pin) {
		this.numero = numero;
		this.saldo = saldo;
		this.pin = pin;
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
	
	/**
	 * Efetua um saque na conta se e somente se o valor passado não for maior que o saldo.
	 */
	public boolean sacar(double valor) {
		if (valor > this.saldo) {
			return false;
		} else {
			this.saldo -= valor;
			return true;
		}
	}
	
	public void depositar(double valor) {
		this.saldo += valor;
	}
	
}
