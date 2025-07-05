package org.example.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Gerenciador de dependências de módulos.
 * Permite adicionar módulos, definir dependências entre eles e listar os módulos cadastrados.
 *
 * @author Bruno Campos
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
}
