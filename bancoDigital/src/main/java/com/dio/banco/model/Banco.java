package com.dio.banco.model;

import com.dio.banco.model.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Banco {
    private String nome;
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public Conta buscarConta (int numero) {
        Optional<Conta> conta = contas.stream()
                .filter(c -> c.getNumero() == numero)
                .findFirst();
        if (conta.isPresent()) {
            return conta.get();
        } else {
            throw new RuntimeException("Conta não encontrada");
        }
    }
}
