package br.com.infnet.java.projeto_de_bloco.model;

/**
 * Classe que efetua uma consulta de saldo para uma determinada conta.
 * @author thiago
 * @see Transacao
 *
 */
public class ConsultaDeSaldo extends Transacao{
	
	public ConsultaDeSaldo(Conta conta) {
		super(conta);
	}

	/**
	 * Executa a consulta do saldo.
	 */
	@Override
	public String executa() {
		String saldoFormatado = mostraMensagemFormatadaParaDolar(this.getConta().getSaldo());
		return "O saldo da conta é: " + saldoFormatado; 
	}

}
