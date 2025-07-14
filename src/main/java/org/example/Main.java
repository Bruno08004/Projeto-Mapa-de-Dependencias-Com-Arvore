package org.example;

import org.example.model.GerenciadorDependencias;

import java.util.Scanner;

/**
 * Classe principal para executar o gerenciador de dependências.
 * Fornece um menu interativo para adicionar módulos, dependências,
 * buscar módulos, remover módulos e imprimir caminhos de build.
 *
 * @author Ana Luiza Freitas Brito Siqueira
 * @author Bruno Campos Penha
 * @author Grazielly de Sousa Barros
 * @author João Gabriel Oliveira Magalhães
 * @author João Vitor Moreira Lemos
 * @author Robert Alves Guimarães
 * @author Vinicius D’Oliveira Rocha
 * @author Emanuel Pinto Nogueira
 * @version 1.0
 * @since 04/07/2025
 */
public class Main {

    private static final GerenciadorDependencias gerenciador = new GerenciadorDependencias();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que inicia o gerenciador de dependências.
     * Exibe o título, o menu e processa as opções escolhidas pelo usuário.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */
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

    /**
     * Exibe o título do gerenciador de dependências.
     */
    private static void exibirTitulo() {
        System.out.println("==============================================");
        System.out.println("          GERENCIADOR DE DEPENDÊNCIAS");
        System.out.println("==============================================");
    }

    /**
     * Exibe o menu de opções disponíveis para o usuário.
     * Inclui opções para adicionar módulos, dependências, visualizar a árvore,
     * buscar módulos, remover módulos e imprimir caminhos de build.
     */
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

    /**
     * Lê a opção escolhida pelo usuário e valida se é um número entre 0 e 6.
     *
     * @return A opção escolhida pelo usuário.
     */
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

    /**
     * Executa a opção escolhida pelo usuário, chamando o método correspondente.
     *
     * @param opcao A opção escolhida pelo usuário.
     */
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

    /**
     * Adiciona um novo módulo ao gerenciador de dependências.
     * Solicita o nome e a versão do módulo, validando se não estão vazios.
     */
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

    /**
     * Adiciona uma dependência entre dois módulos.
     * Solicita os IDs dos módulos dependente (filho) e pai (dependência),
     * validando se não estão vazios.
     */
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
        gerenciador.adicionarDependencia(idFilho, idPai);
    }

    /**
     * Busca um módulo pelo seu ID (nome:versao).
     * Solicita o ID do módulo e chama o método de busca no gerenciador.
     */
    private static void buscarModulo() {
        System.out.print("Digite o ID do módulo para buscar (nome:versao): ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("ID não pode ser vazio.");
            return;
        }
        gerenciador.buscarModulo(id);
    }

    /**
     * Remove um módulo pelo seu ID (nome:versao).
     * Solicita o ID do módulo e chama o método de remoção no gerenciador.
     */
    private static void removerModulo() {
        System.out.print("Digite o ID do módulo para remover (nome:versao): ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("ID não pode ser vazio.");
            return;
        }
        gerenciador.removerModulo(id);
    }

    /**
     * Imprime os caminhos de build dos módulos.
     * Chama o método correspondente no gerenciador para exibir os caminhos.
     */
    private static void imprimirCaminhosDeBuild() {
        System.out.println("\nCaminhos de Build:");
        gerenciador.imprimirCaminhosDeBuild();
    }
}
