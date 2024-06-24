package com.dio.banco;

import com.dio.banco.excecoes.ContaNaoEncontradaException;
import com.dio.banco.excecoes.SaldoInsuficienteException;
import com.dio.banco.model.*;

import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Cliente cliente = new Cliente("Ezequiel", "123.456.789-00");
        Cliente clienteDois = new Cliente("Miguel", "987.654.321-00");

        ContaCorrente contaCorrente = new ContaCorrente(cliente, 1200, 20);
        ContaPoupanca contaPoupanca = new ContaPoupanca(clienteDois, 0.005);

        Banco banco = new Banco("DIO Bank");
        banco.adicionarConta(contaCorrente);
        banco.adicionarConta(contaPoupanca);

        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Ver Saldo");
            System.out.println("4. Aplicar Taxa de Manutencao (apenas Conta Corrente)");
            System.out.println("5. Calcular Rendimento Mensal (apenas Conta Poupanca)");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();

            System.out.println("Escolha a conta: 1 para Conta Corrente, 2 para Conta Poupanca");
            int escolhaConta = scanner.nextInt();

            Conta contaSelecionada = null;
            try {
                contaSelecionada = banco.buscarConta(escolhaConta);
            } catch (ContaNaoEncontradaException e) {
                throw new RuntimeException(e);
            }

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o valor para depositar: ");
                        double valorDeposito = scanner.nextDouble();
                        contaSelecionada.depositar(valorDeposito);
                        System.out.println("Saldo atual: " + contaSelecionada.getSaldo());
                        break;
                    case 2:
                        System.out.print("Digite o valor para sacar: ");
                        double valorSaque = scanner.nextDouble();
                        contaSelecionada.sacar(valorSaque);
                        System.out.println("Saldo atual: " + contaSelecionada.getSaldo());
                        break;
                    case 3:
                        System.out.println("Saldo atual: " + contaSelecionada.getSaldo());
                        break;
                    case 4:
                        if (contaSelecionada instanceof ContaCorrente) {
                            ContaCorrente cc = (ContaCorrente) contaSelecionada;
                            cc.aplicarTaxaManutencao();
                            System.out.println("Taxa de manutenção aplicada. Saldo atual: " + cc.getSaldo());
                        } else {
                            System.out.println("Esta operação não é suportada para esta conta.");
                        }
                        break;
                    case 5:
                        if (contaSelecionada instanceof ContaPoupanca) {
                            ContaPoupanca cp = (ContaPoupanca) contaSelecionada;
                            cp.calcularRendimentoMensal();
                            System.out.println("Rendimento mensal calculado. Saldo atual: " + cp.getSaldo());
                        } else {
                            System.out.println("Esta operação não é suportada para esta conta.");
                        }
                        break;
                    case 0:
                        System.out.println("Programa encerrado.");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (SaldoInsuficienteException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }

        System.out.println("Banco: " + banco.getNome());
        System.out.println("Contas: " + banco.getContas());

        scanner.close();
    }
}