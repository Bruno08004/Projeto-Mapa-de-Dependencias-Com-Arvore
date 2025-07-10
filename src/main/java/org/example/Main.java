package org.example;

import org.example.model.GerenciadorDependencias;

import java.util.Scanner;

/**
 * CLI profissional para o Gerenciador de Dependências,
 * usando exatamente as classes e métodos que você passou.
 * Permite gerenciar módulos e dependências via linha de comando.
 *
 * @author Você
 * @version 1.0
 * @since 2025-07-09
 */
public class Main {

    private static final GerenciadorDependencias gerenciador = new GerenciadorDependencias();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        exibirTitulo();

        while (true) {
            exibirMenu();
            int opcao = lerOpcao();
            if (opcao == 0) {
                System.out.println("Encerrando o programa. Até breve!");
                break;
            }
            executarOpcao(opcao);
        }

        scanner.close();
    }

    private static void exibirTitulo() {
        System.out.println("==============================================");
        System.out.println("          GERENCIADOR DE DEPENDÊNCIAS");
        System.out.println("==============================================");
    }

    private static void exibirMenu() {
        System.out.println("\nMENU:");
        System.out.println("1 - Adicionar módulo");
        System.out.println("2 - Adicionar dependência");
        System.out.println("3 - Visualizar árvore de dependências");
        System.out.println("4 - Buscar módulo");
        System.out.println("5 - Remover módulo");
        System.out.println("6 - Imprimir caminhos de build");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        String entrada = scanner.nextLine();
        try {
            int opcao = Integer.parseInt(entrada);
            if (opcao < 0 || opcao > 6) {
                System.out.println("Opção inválida! Digite um número entre 0 e 6.");
                return lerOpcao();
            }
            return opcao;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite um número.");
            return lerOpcao();
        }
    }

    private static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                adicionarModulo();
                break;
            case 2:
                adicionarDependencia();
                break;
            case 3:
                gerenciador.visualizarArvore();
                break;
            case 4:
                buscarModulo();
                break;
            case 5:
                removerModulo();
                break;
            case 6:
                imprimirCaminhosDeBuild();
                break;
            default:
                System.out.println("Opção não implementada.");
        }
    }

    private static void adicionarModulo() {
        System.out.print("Informe o nome do módulo: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Nome do módulo não pode ser vazio.");
            return;
        }
        System.out.print("Informe a versão do módulo: ");
        String versao = scanner.nextLine().trim();
        if (versao.isEmpty()) {
            System.out.println("Versão do módulo não pode ser vazia.");
            return;
        }
        gerenciador.adicionarModulo(nome, versao);
    }

    private static void adicionarDependencia() {
        System.out.println("Digite os IDs no formato nome:versao");
        System.out.print("ID do módulo dependente (filho): ");
        String idFilho = scanner.nextLine().trim();
        System.out.print("ID do módulo pai (dependência): ");
        String idPai = scanner.nextLine().trim();

        if (idFilho.isEmpty() || idPai.isEmpty()) {
            System.out.println("IDs não podem ser vazios.");
            return;
        }
        // Usando a assinatura original que você tem, passando strings vazias para os params extras
        gerenciador.adicionarDependencia(idFilho, idPai, "", "");
    }

    private static void buscarModulo() {
        System.out.print("Digite o ID do módulo para buscar (nome:versao): ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("ID não pode ser vazio.");
            return;
        }
        gerenciador.buscarModulo(id);
    }

    private static void removerModulo() {
        System.out.print("Digite o ID do módulo para remover (nome:versao): ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("ID não pode ser vazio.");
            return;
        }
        gerenciador.removerModulo(id);
    }

    private static void imprimirCaminhosDeBuild() {
        System.out.println("\nCaminhos de Build:");
        gerenciador.imprimirCaminhosDeBuild();
    }
}
