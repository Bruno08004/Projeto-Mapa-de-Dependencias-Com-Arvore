package org.example;

import org.example.model.GerenciadorDependencias;

import java.util.Scanner;

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

                default:
                    System.out.println("Comando desconhecido.");
            }
        }

        System.out.println("Encerrando...");
    }
}
