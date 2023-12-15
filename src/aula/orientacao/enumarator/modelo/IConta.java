package aula.orientacao.enumarator.modelo;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public interface IConta {
	
	public void depositar(float quantia);
	
	public void sacar(float quantia);
	
	public void extrato(int year, Month month);
	
	public void transferir(float quantia, IConta contaDestino);
	
	public String getNumero();
	
	public void setNumero(String numero);
	
	public boolean isStatus();
	
	public void setStatus(boolean status);
	
	public float getSaldo();
	
	public void setSaldo(float novoSaldo);
	
	public ArrayList<Transacao> getTransacoes();
	
	public void setTransacoes(ArrayList<Transacao> transacoes);
	
}