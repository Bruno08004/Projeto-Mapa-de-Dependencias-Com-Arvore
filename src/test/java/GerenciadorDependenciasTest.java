import static org.junit.jupiter.api.Assertions.*;

import org.example.model.GerenciadorDependencias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Testes unitários para GerenciadorDependencias.
 * Cobre adicionar módulos, dependências, remoção e imprimir caminhos de build.
 */
public class GerenciadorDependenciasTest {

    private GerenciadorDependencias gerenciador;

    // Captura de saída padrão para testar println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        gerenciador = new GerenciadorDependencias();
        System.setOut(new PrintStream(outContent)); // redireciona saída para teste
    }

    @Test
    public void testAdicionarModuloEListar() {
        gerenciador.adicionarModulo("A", "1.0");
        gerenciador.adicionarModulo("B", "2.0");
        gerenciador.adicionarModulo("A", "1.0"); // repetido, não deve adicionar

        String output = outContent.toString();
        assertTrue(output.contains("Módulo adicionado: A:1.0"));
        assertTrue(output.contains("Módulo adicionado: B:2.0"));
        assertTrue(output.contains("Módulo já existe: A:1.0"));
    }

    @Test
    public void testAdicionarDependenciaEVisualizarArvore() {
        gerenciador.adicionarModulo("A", "1.0");
        gerenciador.adicionarModulo("B", "1.0");
        gerenciador.adicionarModulo("C", "1.0");

        gerenciador.adicionarDependencia("B:1.0", "A:1.0");
        gerenciador.adicionarDependencia("C:1.0", "B:1.0");

        outContent.reset();
        gerenciador.visualizarArvore();

        String arvore = outContent.toString();
        // Verifica se a árvore está formatada e correta (níveis de indentação)
        assertTrue(arvore.contains("A:1.0"));
        assertTrue(arvore.contains("  - B:1.0"));
        assertTrue(arvore.contains("    - C:1.0"));
    }

    @Test
    public void testBuscarModulo() {
        gerenciador.adicionarModulo("A", "1.0");
        gerenciador.adicionarModulo("B", "1.0");
        gerenciador.adicionarDependencia("B:1.0", "A:1.0");

        outContent.reset();
        gerenciador.buscarModulo("A:1.0");

        String buscaA = outContent.toString();
        assertTrue(buscaA.contains("Módulo: A:1.0"));
        assertTrue(buscaA.contains("Pai: Nenhum"));
        assertTrue(buscaA.contains("Dependentes:"));
        assertTrue(buscaA.contains("- B:1.0"));

        outContent.reset();
        gerenciador.buscarModulo("B:1.0");
        String buscaB = outContent.toString();
        assertTrue(buscaB.contains("Módulo: B:1.0"));
        assertTrue(buscaB.contains("Pai: A:1.0"));
        assertTrue(buscaB.contains("Dependentes:"));
        assertTrue(buscaB.contains("(Nenhum)"));
    }

    @Test
    public void testRemoverModuloRemoveDependentes() {
        gerenciador.adicionarModulo("A", "1.0");
        gerenciador.adicionarModulo("B", "1.0");
        gerenciador.adicionarModulo("C", "1.0");

        gerenciador.adicionarDependencia("B:1.0", "A:1.0");
        gerenciador.adicionarDependencia("C:1.0", "B:1.0");

        gerenciador.removerModulo("B:1.0");

        outContent.reset();
        gerenciador.visualizarArvore();
        String arvore = outContent.toString();

        // A árvore deve mostrar só A e nenhum B ou C, pois B e seus dependentes foram removidos
        assertTrue(arvore.contains("A:1.0"));
        assertFalse(arvore.contains("B:1.0"));
        assertFalse(arvore.contains("C:1.0"));
    }

    @Test
    public void testImprimirCaminhosDeBuild() {
        gerenciador.adicionarModulo("A", "1.0");
        gerenciador.adicionarModulo("B", "1.0");
        gerenciador.adicionarModulo("C", "1.0");
        gerenciador.adicionarModulo("D", "1.0");

        gerenciador.adicionarDependencia("B:1.0", "A:1.0");
        gerenciador.adicionarDependencia("C:1.0", "A:1.0");
        gerenciador.adicionarDependencia("D:1.0", "B:1.0");

        outContent.reset();
        gerenciador.imprimirCaminhosDeBuild();

        String caminhos = outContent.toString();

        // Deve imprimir os caminhos das folhas: C:1.0 e D:1.0
        assertTrue(caminhos.contains("A:1.0 -> C:1.0"));
        assertTrue(caminhos.contains("A:1.0 -> B:1.0 -> D:1.0"));
        // O módulo A:1.0 não é folha, não aparece sozinho
        assertFalse(caminhos.contains("A:1.0\n"));
    }
}

