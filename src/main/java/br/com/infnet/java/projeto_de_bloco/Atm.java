package br.com.infnet.java.projeto_de_bloco;

import java.util.Optional;

import br.com.infnet.java.projeto_de_bloco.dao.BancoDB;
import br.com.infnet.java.projeto_de_bloco.exception.RecursoNaoEncontradoException;
import br.com.infnet.java.projeto_de_bloco.model.ConsultaDeSaldo;
import br.com.infnet.java.projeto_de_bloco.model.Conta;
import br.com.infnet.java.projeto_de_bloco.model.Deposito;
import br.com.infnet.java.projeto_de_bloco.model.DispensadorDeCedulas;
import br.com.infnet.java.projeto_de_bloco.model.Saque;
import br.com.infnet.java.projeto_de_bloco.model.Teclado;
import br.com.infnet.java.projeto_de_bloco.model.Tela;
import br.com.infnet.java.projeto_de_bloco.model.Transacao;

/**
 * Hello world!
 *
 */
public class Atm {

	private BancoDB bancoDb = BancoDB.getInstance();
	private Tela tela;
	private Teclado teclado;
	private boolean usuarioAutenticado;
	private Conta usuarioLogado;
	private Transacao transacao;
	private DispensadorDeCedulas dispensadorDeCedulas;

	public Atm() {
		this.tela = new Tela();
		this.teclado = new Teclado();
		this.usuarioAutenticado = false;
		this.dispensadorDeCedulas = new DispensadorDeCedulas();
	}

	public static void main(String[] args) {
		Atm atm = new Atm();

		int entradaDoUsuario = 0;

		do {
			atm.tela.showMenu();
			entradaDoUsuario = atm.teclado.getUserInput();
			try {
				atm.executaTarefa(entradaDoUsuario);
			} catch (RecursoNaoEncontradoException e) {
				System.out.println(e.getMessage());
			}
			
		} while (entradaDoUsuario != 4);

		System.out.println("Você saiu com sucesso do sistema ATM.");
		atm.teclado.closeInput();
	}

	private void executaTarefa(int entradaDoUsuario) throws RecursoNaoEncontradoException {

		switch (entradaDoUsuario) {
		case 1:
			consultaSaldo();
			break;
		case 2:
			deposita();
			break;
		case 3:
			saque();
			break;
		case 4:
			break;
		default:
			System.out.println("Comando não encontrado.");
			break;
		}
	}

	private void saque() throws RecursoNaoEncontradoException {
		oUsuarioEstaAutenticado();
		
		this.tela.mostraMensagem("Digite o valor a ser sacado");
		int valorASacar = this.teclado.getUserInput();
		
		if (this.dispensadorDeCedulas.isSufficientCashAvailable(valorASacar)) {
			this.transacao = new Saque(usuarioLogado, valorASacar);
			this.tela.mostraMensagem(this.transacao.executa());
		} else {
			System.out.println("Não existem cédulas suficientes para realizar o saque no ATM.");
		}
		
	}

	private void deposita() throws RecursoNaoEncontradoException {
		
		this.tela.mostraMensagem("Digite o número da conta");
		Conta contaADepositar = this.bancoDb.findConta(this.teclado.getUserInput()).
				orElseThrow(()-> new RecursoNaoEncontradoException("Conta não econtrada!"));
		
		this.tela.mostraMensagem("Entre com o valor a ser depositado");
		int valor = teclado.getUserInput();
		
		this.transacao = new Deposito(contaADepositar, valor);
		this.tela.mostraMensagem(transacao.executa());
	}

	private void consultaSaldo() throws RecursoNaoEncontradoException {

		oUsuarioEstaAutenticado();
		
		this.transacao = new ConsultaDeSaldo(usuarioLogado);
		tela.mostraMensagem(transacao.executa());

	}

	private boolean oUsuarioEstaAutenticado() throws RecursoNaoEncontradoException {
		if (!usuarioAutenticado) {
			if (!autenticaUsuario()) {
				throw new RecursoNaoEncontradoException("Erro ao autenticar para CONTA e PIN informados");
			}
		}
		return true;
	}

	private boolean autenticaUsuario() {
		tela.mostraMensagem("Entre com o número da conta: ");
		int numeroDaConta = teclado.getUserInput();
		tela.mostraMensagem("Entre com o pin da conta.");
		int pin = teclado.getUserInput();

		if (bancoDb.authenticateUser(numeroDaConta, pin)) {
			this.usuarioLogado = bancoDb.findConta(numeroDaConta).get();
			this.usuarioAutenticado = true;
		}

		return usuarioAutenticado;
	}

}
