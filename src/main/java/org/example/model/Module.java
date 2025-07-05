package org.example.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um módulo de software em uma árvore de dependências.
 * Cada módulo pode ter um único módulo pai (do qual depende diretamente)
 * e múltiplos módulos filhos (que dependem diretamente dele).
 * @author João Gabriel Oliveira Magalhães
 * @version 1.0
 * @since 03/07/2025
 */
public class Module {
    private String name;
    private String version;
    private Module parent; // Módulo do qual este módulo depende
    private List<Module> dependencies; // Módulos que dependem deste

    /**
     * Construtor de um módulo.
     * @param name Nome do módulo.
     * @param version Versão do módulo.
     */
    public Module(String name, String version) {
        this.name = name;
        this.version = version;
        this.parent = null;
        this.dependencies = new ArrayList<>();
    }

    // -------------------------------
    // Getters e Setters
    // -------------------------------

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }

    public List<Module> getDependencies() {
        return dependencies;
    }

    // -------------------------------
    // Métodos principais
    // -------------------------------

    /**
     * Adiciona um módulo como dependente deste módulo (filho na árvore).
     * Define também este módulo como pai do dependente.
     * @param module Módulo a ser adicionado como dependente.
     */
    public void addDependency(Module module) {
        dependencies.add(module);
        module.setParent(this);
    }

    /**
     * Remove um módulo da lista de dependentes.
     * Também remove a referência de pai do módulo removido.
     * @param module Módulo a ser removido.
     */
    public void removeDependency(Module module) {
        dependencies.remove(module);
        module.setParent(null);
    }

    /**
     * Verifica se este módulo é uma folha (não possui dependentes).
     * @return true se não possui filhos, false caso contrário.
     */
    public boolean isLeaf() {
        return dependencies.isEmpty();
    }

    /**
     * Retorna o identificador completo do módulo (nome + versão).
     * @return String no formato "Nome:Versão".
     */
    public String getIdentifier() {
        return name + ":" + version;
    }

    @Override
    public String toString() {
        return getIdentifier();
    }
}