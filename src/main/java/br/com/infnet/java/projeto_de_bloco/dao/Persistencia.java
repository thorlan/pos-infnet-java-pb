package br.com.infnet.java.projeto_de_bloco.dao;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.infnet.java.projeto_de_bloco.exception.RecursoNaoEncontradoException;
import br.com.infnet.java.projeto_de_bloco.model.Conta;

/**
 * Responsável por salvar e recuperar as informações sobre as contas mantidas em um .txt
 * @author thiago
 *
 */
public class Persistencia {

	private final static String ACCOUNT_FILE = "Account.txt";
	private final static String recursoNaoEncontradoMsg = "ERRO AO TENTAR ACESSAR O ARQUIVO DE PERSISTÊNCIA. CRIE O ARQUIVO Account.txt NESTE DIRETÓRIO."; 

	/**
	 * Cria o arquivo caso o mesmo não exista.
	 * @return
	 * @throws RecursoNaoEncontradoException
	 */
	private boolean criaSeNaoExistir() throws RecursoNaoEncontradoException {
		
		File accountsFile = new File(ACCOUNT_FILE);
		try {
			return accountsFile.createNewFile();
		} catch (IOException e) {
			throw new RecursoNaoEncontradoException(recursoNaoEncontradoMsg);
		}
	}
	
	/**
	 * Salva os dados da lista de contas no .txt
	 * @param contas
	 * @throws RecursoNaoEncontradoException
	 */
	public void persist(List<Conta> contas) throws RecursoNaoEncontradoException {
		
		criaSeNaoExistir();
		try (FileOutputStream fos = new FileOutputStream(ACCOUNT_FILE); ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(contas);
		} catch (IOException e) {
			throw new RecursoNaoEncontradoException(recursoNaoEncontradoMsg);
		}
	}
	
	/**
	 * Recupera as contas salvas no .txt
	 * @return List<Conta>
	 * @throws RecursoNaoEncontradoException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public List<Conta> getContas() throws   RecursoNaoEncontradoException, ClassNotFoundException{
		
		criaSeNaoExistir();
		List<Conta> contas = null;
		
		try (FileInputStream fin= new FileInputStream (ACCOUNT_FILE)){
			if (fin.available() > 0) {
				ObjectInputStream ois = new ObjectInputStream(fin);
				contas= (ArrayList<Conta>) ois.readObject();
			} else {
				contas = new ArrayList<>();
			}
		} catch (IOException e) {
			throw new RecursoNaoEncontradoException(recursoNaoEncontradoMsg);
		}
		return contas;
	}
}
