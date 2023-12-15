package aula.orientacao.enumarator.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String cpf;
	public String nome;
	public List<IConta> contas;
	
	public Cliente(String cpf, String nome) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.contas = new ArrayList<>();
	}
	
	
	public void adicionarConta(IConta c) {
		if(contas.contains(c)) {
			System.out.println("Conta cadastrada");
		} else {
			contas.add(c);
		}
	}
	
	public void removerConta(IConta c) {
		if(contas.contains(c)) {
			contas.remove(c);
			System.out.println("Conta removida com sucesso!");
		} else {
			System.out.println("Conta inexistente");
		}
	}
	
	public IConta localizarContaPorNumero(String numero) {
		if(contas != null && !contas.isEmpty()) {
			for (IConta conta : contas) {
				if (conta.getNumero().equals(numero))
					return conta;
			}
		}
		return null;
	}
	
	public void atualizarConta(IConta c) {
		
		if(contas.contains(c)) {
			int index = contas.indexOf(c);
			contas.set(index, c);
		}else
			System.out.println("Conta não localizada");
	}


	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", contas=" + contas + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
	
	
}
