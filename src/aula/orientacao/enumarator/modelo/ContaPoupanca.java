package aula.orientacao.enumarator.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Objects;

public class ContaPoupanca implements Serializable, IConta {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String numero;
    float saldo;
    LocalDateTime dataAbertura;
    boolean status;

    private ArrayList<Transacao> transacoes = new ArrayList<>();


    public ContaPoupanca(String numero) {
        super();
        this.numero = numero;
        this.saldo = 0f;
        this.dataAbertura = LocalDateTime.now();
        this.status = true;
    }


    @Override
    public String toString() {
        return "Conta [numero=" + numero + ", saldo=" + saldo + ", dataAbertura=" + dataAbertura + ", status=" + status
                + "]";
    }


    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContaCorrente other = (ContaCorrente) obj;
        return Objects.equals(numero, other.numero);
    }

    public void depositar(float quantia) {
        if (status && quantia > 0) {
            saldo += quantia;
            transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.CREDITO));
            System.out.println("Deposito realizado com sucesso");
        } else
            System.out.println("Deposito não realizado");
    }

    public void sacar(float quantia) {
        if (status && quantia > 0 && quantia <= saldo) {
            saldo -= quantia;
            transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.DEBITO));
            System.out.println("Saque realizado com sucesso");
        } else
            System.out.println("Saque nao pode ser realizado");
    }

    public void extrato(int year, Month month) {
        for (Transacao t : transacoes) {
            if (t.dataTransacao.getYear() == year && t.dataTransacao.getMonth() == month) {
                System.out.println(t);
            }
        }
    }

    public void transferir(float quantia, IConta contaDestino) {
        if (this.status) {
            if (quantia >= 0 && quantia <= this.saldo) {
                if (contaDestino.isStatus()) {
                    this.saldo -= quantia;
                    contaDestino.setSaldo(contaDestino.getSaldo() + quantia);
                    this.transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_DEBITO, contaDestino));
                    contaDestino.getTransacoes().add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_CREDITO, this));

                    // Tarifa de 3% da quantia se a transferencia for de Poupança para Corrente
                    if (contaDestino instanceof ContaCorrente)
                        this.saldo -= quantia * 0.03;

                } else {
                    System.out.println("Conta de destino inativa!");
                }
            } else {
                System.out.println("Saldo insuficiente!");
            }
        } else {
            System.out.println("Conta inativa!");
        }
    }
    public String getNumero() {
        return numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }


    public float getSaldo() {
        return saldo;
    }


    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }


    public boolean isStatus() {
        return status;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }


    public ArrayList<Transacao> getTransacoes() {
        return transacoes;
    }


    public void setTransacoes(ArrayList<Transacao> transacoes) {
        this.transacoes = transacoes;
    }


}
