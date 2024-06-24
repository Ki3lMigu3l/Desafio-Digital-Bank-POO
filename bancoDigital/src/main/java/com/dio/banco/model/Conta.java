package com.dio.banco.model;

import com.dio.banco.excecoes.SaldoInsuficienteException;
import com.dio.banco.interfaces.IConta;
import lombok.ToString;

@ToString
public abstract class Conta implements IConta {
    private static int SEQUENCIAL = 1;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }
        saldo -= valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) throws SaldoInsuficienteException {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public int getNumero() {
        return numero;
    }
}
