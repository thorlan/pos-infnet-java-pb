package br.com.infnet.java.projeto_de_bloco.model;

/**
 * @author thiago
 * Esta classe efetua uma consulta de saldo.
 */
public class ConsultaDeSaldo extends Transacao{
	
	public ConsultaDeSaldo(Conta conta) {
		super(conta);
	}

	@Override
	public String executa() {
		String saldoFormatado = mostraMensagemFormatadaParaDolar(this.getConta().getSaldo());
		return "O saldo da conta é: " + saldoFormatado; 
	}

}
