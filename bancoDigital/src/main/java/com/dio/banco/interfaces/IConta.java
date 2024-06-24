package com.dio.banco.interfaces;

import com.dio.banco.excecoes.SaldoInsuficienteException;

public interface IConta {
    void depositar(double valor);
    void sacar(double valor) throws SaldoInsuficienteException;
    void transferir(double valor, IConta contaDestino) throws SaldoInsuficienteException;
    double getSaldo();
    int getNumero();
}
