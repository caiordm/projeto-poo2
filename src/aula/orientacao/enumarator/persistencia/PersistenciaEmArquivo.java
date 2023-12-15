package aula.orientacao.enumarator.persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import aula.orientacao.enumarator.modelo.Cliente;


public class PersistenciaEmArquivo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private List<Cliente> cadastroClientes = new ArrayList<>();

	private static PersistenciaEmArquivo instance;

	private PersistenciaEmArquivo() {
		carregarDadosDeArquivo();
	}

	public static PersistenciaEmArquivo getInstance() {
		if(instance!=null)
			return instance;
		else
			return new PersistenciaEmArquivo();
	}

	public void getClientesCadastrados() {
		for (Cliente c : cadastroClientes) {
			System.out.println(c);
		}
	}

	public List<Cliente> getClienteCadastrados(){
		return cadastroClientes;
	}

    public void removerCliente (Cliente c) {
        if(cadastroClientes.contains(c)) {
            cadastroClientes.remove(c);
            salvarDadosEmArquivo();
        }else
            System.err.println("Cliente não encontrado!");
    }


	public void salvarCliente(Cliente c) {
		if (!cadastroClientes.contains(c)) {
			cadastroClientes.add(c);
			salvarDadosEmArquivo();
		} else
			System.err.println("Cliente ja cadastrado no sistema!");
	}

	public Cliente localizarClientePorCPF(String cpf) {
		Cliente c = new Cliente(cpf, null);
		if(cadastroClientes.contains(c)) {
			int index = cadastroClientes.indexOf(c);
			c = cadastroClientes.get(index);
			return c;
		}else
			return null;
	}

	public void atualizarCliente(Cliente c) {
		if(cadastroClientes.contains(c)) {
			int index = cadastroClientes.indexOf(c);
			cadastroClientes.set(index, c);
			salvarDadosEmArquivo();
		}else
			System.err.println("Cliente não encontrado!");
	}

	public void salvarDadosEmArquivo() {
		try {
			FileOutputStream fos = new FileOutputStream("dados");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(cadastroClientes);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void carregarDadosDeArquivo() {
		try {
			FileInputStream fis = new FileInputStream("dados");
			ObjectInputStream ois = new ObjectInputStream(fis);

			// Verifica se o arquivo não está vazio antes de ler os dados
			if (fis.available() > 0) {
				cadastroClientes = (List<Cliente>) ois.readObject();
			} else {
				System.out.println("O arquivo está vazio ou não contém dados válidos.");
				// Pode inicializar a lista de clientes com uma lista vazia, por exemplo:
				cadastroClientes = new ArrayList<>();
			}

			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// Tratamento para arquivo não encontrado
			e.printStackTrace();
		} catch (EOFException e) {
			// Tratamento para final de arquivo inesperado (arquivo vazio)
			System.out.println("Final de arquivo inesperado (possível arquivo vazio).");
			// Inicializar a lista de clientes com uma lista vazia, por exemplo:
			cadastroClientes = new ArrayList<>();
		} catch (IOException e) {
			// Tratamento para outros erros de leitura ou escrita de arquivo
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// Tratamento para classe não encontrada ao desserializar o objeto
			e.printStackTrace();
		}
	}


}