package br.com.infnet.java.projeto_de_bloco;

import java.util.InputMismatchException;

import br.com.infnet.java.projeto_de_bloco.dao.BancoDB;
import br.com.infnet.java.projeto_de_bloco.exception.RecursoNaoEncontradoException;
import br.com.infnet.java.projeto_de_bloco.exception.ValorInvalidoException;
import br.com.infnet.java.projeto_de_bloco.model.ConsultaDeSaldo;
import br.com.infnet.java.projeto_de_bloco.model.Conta;
import br.com.infnet.java.projeto_de_bloco.model.Deposito;
import br.com.infnet.java.projeto_de_bloco.model.DispensadorDeCedulas;
import br.com.infnet.java.projeto_de_bloco.model.Saque;
import br.com.infnet.java.projeto_de_bloco.model.Teclado;
import br.com.infnet.java.projeto_de_bloco.model.Tela;
import br.com.infnet.java.projeto_de_bloco.model.Transacao;

/**
 * Representa o atm e suas funcionalidades. Todas as funções do sistema passam por esta classe.
 * @author thiago
 * @see BancoDB
 * @see Tela
 * @see Teclado
 * @see Conta
 * @see Transacao
 * @see DispensadorDeCedulas
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
		
		do {
			atm.tela.delimitador();
			try {
				if (atm.usuarioAutenticado) {
					atm.menuParaUsuarioLogado(atm);
				} else {
					atm.menuPadrao(atm);
				}
			} catch (RecursoNaoEncontradoException | ValorInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Somente números são aceitos");
				atm.teclado = new Teclado();
			}

		} while (true);

	}

	/**
	 * Mostra o menu padrão para o usuário não autenticado e lê a entrada do mesmo.
	 * @param atm
	 * @throws RecursoNaoEncontradoException
	 * @throws ValorInvalidoException
	 */
	private void menuPadrao(Atm atm) throws RecursoNaoEncontradoException, ValorInvalidoException {
		atm.tela.showMenuPadrao();
		atm.tela.mostraMensagem("Digite a opção desejada:");
		Integer entradaDoUsuario = atm.teclado.getUserInput();

		atm.executaTarefaUsuarioNaoLogado(entradaDoUsuario);

	}

	/**
	 * Mostra o menu para o usuário autenticado e lê a entrada do mesmo.
	 * @param atm
	 * @throws RecursoNaoEncontradoException
	 * @throws ValorInvalidoException
	 */
	private void menuParaUsuarioLogado(Atm atm) throws RecursoNaoEncontradoException, ValorInvalidoException {
		atm.tela.mostraMensagemDeBoasVindas(usuarioLogado);
		atm.tela.showMenu();
		atm.tela.mostraMensagem("Digite a opção desejada:");
		Integer entradaDoUsuario = atm.teclado.getUserInput();
		
		atm.executaTarefa(entradaDoUsuario);

	}

	/**
	 * Executa a entrada do usuário não autenticado.
	 * @param entradaDoUsuario
	 * @throws RecursoNaoEncontradoException
	 * @throws ValorInvalidoException
	 */
	private void executaTarefaUsuarioNaoLogado(int entradaDoUsuario) throws RecursoNaoEncontradoException, ValorInvalidoException {
		switch (entradaDoUsuario) {
		case 1:
			oUsuarioEstaAutenticado();
			break;
		case 2:
			tela.mostraMensagem("Opção escolhida (2) Depósito:");
			deposita();
			break;
		default:
			System.out.println("Comando não encontrado.");
			break;
		}

	}

	/**
	 * Executa a entrada do usuário.
	 * @param entradaDoUsuario
	 * @throws RecursoNaoEncontradoException
	 * @throws ValorInvalidoException
	 */
	private void executaTarefa(int entradaDoUsuario) throws RecursoNaoEncontradoException, ValorInvalidoException {

		switch (entradaDoUsuario) {
		case 1:
			tela.mostraMensagem("Opção escolhida (1) Consulta de saldo:");
			consultaSaldo();
			break;
		case 2:
			tela.mostraMensagem("Opção escolhida (2) Depósito:");
			deposita();
			break;
		case 3:
			tela.mostraMensagem("Opção escolhida (3) Saque:");
			saque();
			break;
		case 4:
			this.usuarioAutenticado = false;
			this.usuarioLogado = null;
			break;
		default:
			System.out.println("Comando não encontrado.");
			break;
		}
	}

	/**
	 * Efetua o saque na conta do usuário autenticado.
	 * @throws RecursoNaoEncontradoException
	 * @throws ValorInvalidoException
	 */
	private void saque() throws RecursoNaoEncontradoException, ValorInvalidoException {

		this.tela.mostraMensagem("Digite o valor a ser sacado");
		int valorASacar = this.teclado.getUserInput();

		if (this.dispensadorDeCedulas.dispenseCash(valorASacar)) {
			this.transacao = new Saque(usuarioLogado, valorASacar, bancoDb);
			this.tela.mostraMensagem(this.transacao.executa());
		} else {
			System.out.println("Não existem cédulas suficientes para realizar o saque no ATM.");
		}

	}

	/**
	 * Efetua o depósito para conta e valor passado.
	 * @throws RecursoNaoEncontradoException
	 * @throws ValorInvalidoException
	 */
	private void deposita() throws RecursoNaoEncontradoException, ValorInvalidoException {

		this.tela.mostraMensagem("Digite o número da conta");
		Conta contaADepositar = this.bancoDb.findConta(this.teclado.getUserInput())
				.orElseThrow(() -> new RecursoNaoEncontradoException("Conta não econtrada!"));

		this.tela.mostraMensagem("Entre com o valor a ser depositado na conta: " + contaADepositar.getNumero());
		int valor = teclado.getUserInput();

		this.transacao = new Deposito(contaADepositar, valor, bancoDb);
		this.tela.mostraMensagem(transacao.executa());
	}

	/**
	 * Consulta saldo para a conta do usuário autenticado.
	 * @throws RecursoNaoEncontradoException
	 */
	private void consultaSaldo() throws RecursoNaoEncontradoException {

		this.transacao = new ConsultaDeSaldo(usuarioLogado);
		tela.mostraMensagem(transacao.executa());

	}

	/**
	 * Verifica se o usuário está autenticado.
	 * @return
	 * @throws RecursoNaoEncontradoException
	 */
	private boolean oUsuarioEstaAutenticado() throws RecursoNaoEncontradoException {
		if (!usuarioAutenticado) {
			if (!autenticaUsuario()) {
				throw new RecursoNaoEncontradoException("Erro ao autenticar para CONTA e PIN informados");
			}
		}
		this.usuarioAutenticado = true;
		return usuarioAutenticado;
	}

	/**
	 * Efetua a autenticação do usuário.
	 * @return
	 */
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
