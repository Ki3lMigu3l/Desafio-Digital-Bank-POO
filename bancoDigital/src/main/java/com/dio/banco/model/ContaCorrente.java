package com.dio.banco.model;


public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;
    private double taxaManutencao;

    public ContaCorrente(Cliente cliente, double limiteChequeEspecial, double taxaManutencao) {
        super(cliente);
        this.limiteChequeEspecial = limiteChequeEspecial;
        this.taxaManutencao = taxaManutencao;
    }

    public double getChequeEspecial () {
        return limiteChequeEspecial;
    }

    public double getChequeEspecialDisponivel() {
        return saldo + limiteChequeEspecial;
    }

    public void aplicarTaxaManutencao() {
        saldo -= taxaManutencao;
    }
}
