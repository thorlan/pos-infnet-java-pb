package br.com.infnet.java.projeto_de_bloco.model;

import br.com.infnet.java.projeto_de_bloco.exception.RecursoNaoEncontradoException;

/**
 * @author thiago
 * Esta classe uma consulta de saldo.
 */
public class ConsultaDeSaldo extends Transacao{
	
	public ConsultaDeSaldo(Conta conta, double valor) throws RecursoNaoEncontradoException {
		super(conta, valor);
	}
	
	public ConsultaDeSaldo(Conta conta) {
		super(conta);
	}

	@Override
	public String executa() {
		String saldoFormatado = mostraMensagemFormatadaParaDolar(this.getConta().getSaldo());
		return "O saldo da conta é: " + saldoFormatado; 
	}

}
