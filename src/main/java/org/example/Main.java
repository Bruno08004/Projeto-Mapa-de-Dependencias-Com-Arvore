package org.example;

import java.util.Scanner;

import org.example.model.GerenciadorDependencias;

public class Main {
    public static void main(String[] args) {
        GerenciadorDependencias gerenciador = new GerenciadorDependencias();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Gerenciador de Dependências ===");

        while (true) {
            System.out.print("\n> ");
            String entrada = scanner.nextLine();
            String[] partes = entrada.trim().split(" ");

            if (partes[0].equalsIgnoreCase("sair")) break;

            switch (partes[0]) {
                case "adicionarModulo":
                    if (partes.length == 3)
                        gerenciador.adicionarModulo(partes[1], partes[2]);
                    else
                        System.out.println("Uso: adicionarModulo <nome> <versao>");
                    break;

                case "adicionarDependencia":
                    if (partes.length == 3)
                        gerenciador.adicionarDependencia(partes[1], partes[2]);
                    else
                        System.out.println("Uso: adicionarDependencia <modulo-filho> <modulo-pai>");
                    break;

                case "listar":
                    gerenciador.listarModulos();
                    break;
                // === grazy ===
                case "visualizar":
                    gerenciador.visualizarArvore();
                    break;
                case "buscar":
                    if (partes.length == 2)
                        gerenciador.buscarModulo(partes[1]);
                    else
                        System.out.println("Uso: buscar <nome:versao>");
                    break;
                case "remover":
                    if (partes.length == 2)
                        gerenciador.removerModulo(partes[1]);
                    else
                        System.out.println("Uso: remover <nome:versao>");
                    break;
                // === grazy ===
                default:
                    System.out.println("Comando desconhecido.");
            }
        }

        System.out.println("Encerrando...");
    }
}
