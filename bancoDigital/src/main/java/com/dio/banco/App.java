package com.dio.banco;

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
            System.out.println("1. Depositar na Conta Corrente");
            System.out.println("2. Sacar da Conta Corrente");
            System.out.println("3. Ver Saldo da Conta Corrente");
            System.out.println("4. Ver Cheque Especial Disponível");
            System.out.println("5. Aplicar Taxa de Manutenção");
            System.out.println("6. Depositar na Conta Poupança");
            System.out.println("7. Calcular Rendimento Mensal da Poupança");
            System.out.println("8. Ver Saldo da Conta Poupança");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            double valor;

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor para depositar na Conta Corrente: ");
                    valor = scanner.nextDouble();
                    contaCorrente.depositar(valor);
                    System.out.println("Saldo atual da Conta Corrente: " + contaCorrente.getSaldo());
                    break;

                case 2:
                    System.out.print("Digite o valor para sacar da Conta Corrente: ");
                    valor = scanner.nextDouble();
                    try {
                        contaCorrente.sacar(valor);
                        System.out.println("Saldo atual da Conta Corrente: " + contaCorrente.getSaldo());
                    } catch (SaldoInsuficienteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Saldo atual da Conta Corrente: " + contaCorrente.getSaldo());
                    break;

                case 4:
                    System.out.println("Cheque especial disponível: " + contaCorrente.getChequeEspecialDisponivel());
                    break;

                case 5:
                    contaCorrente.aplicarTaxaManutencao();
                    System.out.println("Taxa de manutenção aplicada. Saldo atual da Conta Corrente: " + contaCorrente.getSaldo());
                    break;

                case 6:
                    System.out.print("Digite o valor para depositar na Conta Poupança: ");
                    valor = scanner.nextDouble();
                    contaPoupanca.depositar(valor);
                    System.out.println("Saldo atual da Conta Poupança: " + contaPoupanca.getSaldo());
                    break;

                case 7:
                    contaPoupanca.calcularRendimentoMensal();
                    System.out.println("Rendimento mensal calculado. Saldo atual da Conta Poupança: " + contaPoupanca.getSaldo());
                    break;

                case 8:
                    System.out.println("Saldo atual da Conta Poupança: " + contaPoupanca.getSaldo());
                    break;

                case 0:
                    continuar = false;
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        System.out.println(banco.buscarConta(1));
        System.out.println(banco.buscarConta(2));

        scanner.close();
    }
}