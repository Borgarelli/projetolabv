package br.gov.sp.fatec.projetolab5.service;
// Importa as classes necessárias para o funcionamento do código
//Para rodar o jacoco, basta acessar a pasta target, depois ste/jacoco e clicar emcima de index.html com o botão direito e clicar em live server
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;
import br.gov.sp.fatec.projetolab5.repository.AutorizacaoRepository;

public class AutorizacaoServiceTest {

    // Injeta a dependência AutorizacaoService para ser utilizada no teste
    @Autowired
    private AutorizacaoService service;

    // Cria um objeto mock do AutorizacaoRepository para ser utilizado no teste
    @MockBean
    private AutorizacaoRepository autorizacaoRepo;

    // Método executado antes de cada teste
    @BeforeEach
    public void setUp() {
        // Cria um objeto Autorizacao para ser utilizado no teste
        Autorizacao autorizacao = new Autorizacao();
        autorizacao.setId(1L);
        autorizacao.setNome("Teste");
        // Cria uma lista de Autorizacoes e adiciona o objeto Autorizacao criado anteriormente
        List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();
        autorizacoes.add(autorizacao);
    }
}
