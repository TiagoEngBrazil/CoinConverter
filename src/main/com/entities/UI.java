package main.com.entities;

import java.util.Scanner;

import static main.com.entities.ClearScreen.centralizarTexto;

public class UI {
    public static void inicio() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            exibirMenu();

            op = sc.nextInt();
            switch (op) {
                case 1:
                    converterMoeda("BRL", "USD", sc, "R$", "U$");
                    break;
                case 2:
                    converterMoeda("USD", "BRL", sc, "U$", "R$");
                    break;
                case 3:
                    converterMoeda("BRL", "ARS", sc, "R$", "$");
                    break;
                case 4:
                    converterMoeda("ARS", "BRL", sc, "$", "R$");
                    break;
                case 5:
                    converterMoeda("BRL", "EUR", sc, "R$", "€");
                    break;
                case 6:
                    converterMoeda("EUR", "BRL", sc, "€", "R$");
                    break;
                case 7:
                    converterMoeda("BRL", "UYU", sc, "R$", "$U");
                    break;
                case 8:
                    converterMoeda("UYU", "BRL", sc, "$U", "R$");
                    break;
                case 9:
                    converterMoeda("BRL", "CAD", sc, "R$", "C$");
                    break;
                case 10:
                    converterMoeda("CAD", "BRL", sc, "C$", "R$");
                    break;
                case 11:
                    converterMoeda("BRL", "CLP", sc, "R$", "$");
                    break;
                case 12:
                    converterMoeda("CLP", "BRL", sc, "$", "R$");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (op != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n*************************************\n");
        System.out.println("Bem vindo ao super convetor de moedas\n");
        System.out.print("""
                1) Real -> Dólar
                2) Dólar -> Real
                3) Real -> Peso argentino
                4) Peso argentino -> Real
                5) Real -> Euro
                6) Euro -> Real
                7) Real -> Peso uruguaio
                8) Peso uruguaio -> Real
                9) Real -> Dólar canadense
                10) Dólar canadense -> Real
                11) Real -> Peso chileno
                12) Peso chileno -> Real
                0) Sair
                                      
                Escolha uma opção:\s""");
    }

    private static void converterMoeda(String from, String to, Scanner sc, String symbolFrom, String symbolTo) {
        try {
            Double currency = CoinConverterService.currencyApi(from, to);
            System.out.print("Digite o valor que quer converter: ");
            sc.nextLine();
            String moedaString = sc.nextLine().replace(",", ".");

            Double moeda = Double.parseDouble(moedaString);

            String texto = String.format("O valor de %s %.2f convertido é: %s %.2f", symbolFrom, moeda, symbolTo, (moeda * currency));

            System.out.println();
            centralizarTexto("************************************ ");
            centralizarTexto(texto);
            centralizarTexto("Enter para retornar");
            centralizarTexto("************************************ ");
            sc.nextLine();
            ClearScreen.clearScreen();

        } catch (NumberFormatException e) {
            System.out.println("Erro: Valor digitado é inválido.");
        } catch (Exception e) {
            System.out.println("Erro ao converter moeda: " + e.getMessage());
        }
    }
}
