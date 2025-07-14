package org.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um módulo de software em uma árvore de dependências.
 * Cada módulo pode ter um único módulo pai (do qual depende diretamente)
 * e múltiplos módulos filhos (que dependem diretamente dele).
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
 * @since 03/07/2025
 */
public class Modulo {
    private String nome;
    private String versao;
    private Modulo pai; // Módulo do qual este módulo depende
    private List<Modulo> dependentes; // Módulos que dependem deste

    /**
     * Construtor de um módulo.
     *
     * @param nome   Nome do módulo.
     * @param versao Versão do módulo.
     */
    public Modulo(String nome, String versao) {
        this.nome = nome;
        this.versao = versao;
        this.pai = null;
        this.dependentes = new ArrayList<>();
    }

    /**
     * Retorna o nome do módulo.
     *
     * @return Nome do módulo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a versão do módulo.
     *
     * @return Versão do módulo.
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Retorna o módulo pai deste módulo.
     *
     * @return Módulo pai ou null se não houver.
     */
    public Modulo getPai() {
        return pai;
    }

    /**
     * Define o módulo pai deste módulo.
     *
     * @param pai Módulo pai a ser definido.
     */
    public void setPai(Modulo pai) {
        this.pai = pai;
    }

    /**
     * Retorna a lista de módulos dependentes deste módulo.
     *
     * @return Lista de dependentes.
     */
    public List<Modulo> getDependentes() {
        return dependentes;
    }

    /**
     * Adiciona um módulo como dependente deste módulo (filho na árvore).
     * Define também este módulo como pai do dependente.
     *
     * @param modulo Módulo a ser adicionado como dependente.
     */
    public void adicionarDependente(Modulo modulo) {
        dependentes.add(modulo);
        modulo.setPai(this);
    }

    /**
     * Remove um módulo da lista de dependentes.
     * Também remove a referência de pai do módulo removido.
     *
     * @param modulo Módulo a ser removido.
     */
    public void removerDependente(Modulo modulo) {
        dependentes.remove(modulo);
        modulo.setPai(null);
    }

    /**
     * Verifica se este módulo é uma folha (não possui dependentes).
     *
     * @return true se não possui filhos, false caso contrário.
     */
    public boolean ehFolha() {
        return dependentes.isEmpty();
    }

    /**
     * Retorna o identificador completo do módulo (nome + versão).
     *
     * @return String no formato "Nome:Versão".
     */
    public String getIdentificador() {
        return nome + ":" + versao;
    }

    @Override
    public String toString() {
        return getIdentificador();
    }
}