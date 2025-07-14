package org.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gerenciador de dependências de módulos.
 * Permite adicionar módulos, definir dependências entre eles e listar os módulos cadastrados.
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
public class GerenciadorDependencias {
    private Map<String, Modulo> modulos = new HashMap<>();

    /**
     * Adiciona um novo módulo ao gerenciador.
     *
     * @param nome   Nome do módulo.
     * @param versao Versão do módulo.
     */
    public void adicionarModulo(String nome, String versao) {
        String id = nome + ":" + versao;
        if (modulos.containsKey(id)) {
            System.out.println("Módulo já existe: " + id);
            return;
        }
        Modulo novoModulo = new Modulo(nome, versao);
        modulos.put(id, novoModulo);
        System.out.println("Módulo adicionado: " + id);
    }

    /**
     * Adiciona uma dependência entre dois módulos.
     * O módulo filho passa a depender do módulo pai.
     *
     * @param idFilho Identificador do módulo filho (dependente).
     * @param idPai   Identificador do módulo pai (dependência).
     */
    public void adicionarDependencia(String idFilho, String idPai) {
        Modulo filho = modulos.get(idFilho);
        Modulo pai = modulos.get(idPai);

        if (filho == null || pai == null) {
            System.out.println("Um ou ambos os módulos não foram encontrados.");
            return;
        }

        if (filho.getPai() != null) {
            System.out.println("O módulo " + idFilho + " já depende de " + filho.getPai().getIdentificador());
            return;
        }

        pai.adicionarDependente(filho);
        System.out.println("Dependência adicionada: " + idFilho + " -> " + idPai);
    }

    /**
     * Lista todos os módulos cadastrados no gerenciador.
     */
    public void listarModulos() {
        System.out.println("\nMódulos cadastrados:");
        for (String chave : modulos.keySet()) {
            System.out.println("- " + chave);
        }
    }


    /**
     * Exibe a árvore de dependências a partir dos módulos raiz.
     */
    public void visualizarArvore() {
        System.out.println("\nÁrvore de Dependências:");
        boolean encontrouRaiz = false;
        for (Modulo modulo : modulos.values()) {
            if (modulo.getPai() == null) {
                encontrouRaiz = true;
                exibirArvore(modulo, 0);
            }
        }
        if (!encontrouRaiz) {
            System.out.println("(Nenhum módulo raiz encontrado)");
        }
    }

    /**
     * Exibe a árvore de dependências de um módulo recursivamente.
     *
     * @param modulo Módulo atual a ser exibido.
     * @param nivel  Nível de profundidade na árvore (usado para indentação).
     */
    private void exibirArvore(Modulo modulo, int nivel) {
        System.out.println("  ".repeat(nivel) + "- " + modulo.getIdentificador());
        for (Modulo dependente : modulo.getDependentes()) {
            exibirArvore(dependente, nivel + 1);
        }
    }

    /**
     * Busca e exibe informações de um módulo pelo identificador.
     *
     * @param id Identificador no formato "nome:versao"
     */
    public void buscarModulo(String id) {
        Modulo modulo = modulos.get(id);
        if (modulo == null) {
            System.out.println("Módulo não encontrado: " + id);
            return;
        }
        System.out.println("Módulo: " + modulo.getIdentificador());
        System.out.println("Pai: " + (modulo.getPai() != null ? modulo.getPai().getIdentificador() : "Nenhum"));
        System.out.println("Dependentes:");
        if (modulo.getDependentes().isEmpty()) {
            System.out.println("  (Nenhum)");
        } else {
            for (Modulo dep : modulo.getDependentes()) {
                System.out.println("  - " + dep.getIdentificador());
            }
        }
    }

    /**
     * Remove um módulo do gerenciador e atualiza as dependências.
     *
     * @param id Identificador do módulo a ser removido.
     */
    public void removerModulo(String id) {
        Modulo modulo = modulos.get(id);
        if (modulo == null) {
            System.out.println("Módulo não encontrado: " + id);
            return;
        }
        // Remove recursivamente todos os dependentes do módulo
        for (Modulo dependente : new ArrayList<>(modulo.getDependentes())) {
            removerModulo(dependente.getIdentificador());
        }
        // Remove referência do pai
        if (modulo.getPai() != null) {
            modulo.getPai().removerDependente(modulo);
        }
        modulos.remove(id);
        System.out.println("Módulo removido: " + id);
    }

    /**
     * Imprime todos os caminhos de build a partir dos módulos raiz.
     * Um caminho de build é uma sequência de módulos do módulo raiz até um módulo folha.
     */
    public void imprimirCaminhosDeBuild() {
        for (Modulo modulo : modulos.values()) {
            if (modulo.getDependentes().isEmpty()) {
                imprimirCaminho(modulo);
            }
        }
    }

    /**
     * Imprime o caminho de build a partir de um módulo até a raiz.
     *
     * @param modulo Módulo folha a partir do qual o caminho será impresso.
     */
    private void imprimirCaminho(Modulo modulo) {
        List<String> caminho = new ArrayList<>();
        while (modulo != null) {
            caminho.add(0, modulo.getNome() + ":" + modulo.getVersao());
            modulo = modulo.getPai();
        }
        System.out.println(String.join(" -> ", caminho));
    }

}
