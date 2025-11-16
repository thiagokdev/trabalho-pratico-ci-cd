package br.com.cleanservice.cleanservice;

// --- Importações Necessárias ---
import br.com.cleanservice.cleanservice.OrdemServico;
import br.com.cleanservice.cleanservice.OrdemServicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.*;

// @DataJpaTest: Configura o teste focado APENAS no Banco de Dados (Mais rápido e preciso)
@DataJpaTest
// AutoConfigureTestDatabase: Garante que vamos usar o H2 (memória) e não o seu MySQL real
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
// Força a configuração do H2 e a criação das tabelas
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.show-sql=true",
    "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
})
class CleanserviceApplicationTests {

    @Autowired
    private OrdemServicoRepository repository;

    @Test
    void testeCriarOrdemServico() {
        System.out.println("--- INICIANDO TESTE DE BANCO DE DADOS ---");

        // 1. CRIAR (Cenário)
        OrdemServico os = new OrdemServico();
        os.setNomeCliente("Cliente Teste");
        os.setDescricaoServico("Teste Unitário");
        os.setValor(50.0);

        // 2. EXECUTAR (Ação)
        OrdemServico salva = repository.save(os);

        // 3. VALIDAR (Check)
        assertNotNull(salva.getId()); // O ID deve ter sido gerado
        assertEquals("Cliente Teste", salva.getNomeCliente()); // O nome deve ser igual
        
        System.out.println("✅ SUCESSO! ID GERADO: " + salva.getId());
        System.out.println("--- FIM DO TESTE ---");
    }
}