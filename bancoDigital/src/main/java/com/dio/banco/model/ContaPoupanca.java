package com.dio.banco.model;

public class ContaPoupanca extends Conta{
    private double taxaJuros;

    public ContaPoupanca(Cliente cliente, double taxaJuros) {
        super(cliente);
        this.taxaJuros = taxaJuros;
    }

    public void calcularRendimentoMensal() {
        saldo += saldo * taxaJuros;
    }
}
