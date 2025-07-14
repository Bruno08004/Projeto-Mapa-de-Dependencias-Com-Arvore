# 💻 Mapa de Dependências de Software

## Descrição

Este projeto em Java implementa um sistema para gerenciamento de dependências entre módulos de software, estruturado em formato de árvore. Cada módulo pode depender diretamente de, no máximo, um outro módulo, formando uma hierarquia de dependências. A interação ocorre por meio de uma interface de linha de comando (CLI), proporcionando simplicidade e agilidade no uso.

O objetivo é oferecer uma ferramenta capaz de:

- Gerenciar módulos e suas versões;
- Definir relações de dependência entre módulos;
- Visualizar a hierarquia completa das dependências;
- Buscar informações detalhadas de qualquer módulo;
- Remover módulos junto com todos os seus dependentes;
- Imprimir todos os caminhos de build que refletem a ordem de compilação ou montagem.

---

## ⚙️ Funcionalidades Principais

- **Cadastro de Módulos:** Inserção de módulos identificados por nome e versão.
- **Definição de Dependências:** Associação de módulos dependentes, respeitando a regra de dependência única.
- **Visualização da Árvore de Dependências:** Exibição hierárquica com níveis de profundidade.
- **Busca de Módulos:** Consulta detalhada pelo identificador único (`nome:versao`).
- **Remoção Recursiva:** Exclusão de módulos e seus dependentes para manutenção da consistência.
- **Impressão de Caminhos de Build:** Listagem de todas as sequências de dependência desde raízes até folhas.

---

## Instruções de Uso

1. Execute a aplicação Java a partir da classe principal `Main`.
2. Interaja com o sistema por meio do menu exibido no terminal.
3. Utilize o formato padrão `nome:versao` para identificar módulos (exemplo: `BibliotecaX:2.1`).

---

## Exemplo de Fluxo de Uso

- Adicionar módulo `Core:1.0`.
- Adicionar módulo `UI:1.0`.
- Definir dependência: `UI:1.0` depende de `Core:1.0`.
- Visualizar a árvore para confirmar a estrutura.
- Imprimir caminhos de build para verificar a ordem de compilação.

---


## 👥 Autoria

### Projeto desenvolvido por: 

- Ana Luiza Freitas
- Bruno Campos
- Emanuel Pinto
- Grazielly Barros
- João Gabriel Oliveira
- João Vitor Moreira
- Robert Guimarães
- Vinicius D Oliveira

---

## Considerações Finais

Este sistema é uma base robusta para estudos e aplicações acadêmicas sobre gerenciamento de dependências e estruturas hierárquicas em software. Pode ser ampliado para incluir funcionalidades como persistência em arquivos, interface gráfica, ou integração contínua.

---



