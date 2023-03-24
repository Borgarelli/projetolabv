package br.gov.sp.fatec.projetolab5.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;
import br.gov.sp.fatec.projetolab5.repository.AutorizacaoRepository;

public class AutorizacaoServiceTest {
    @Autowired
    private AutorizacaoService service;

    @MockBean
    private AutorizacaoRepository autorizacaoRepo;

    @BeforeEach
    public void setUp() {
        Autorizacao autorizacao = new Autorizacao();
        autorizacao.setId(1L);
        autorizacao.setNome("Teste");
        List<Autorizacao> autorizacoes = new ArrayList<Autorizacao>();
        autorizacoes.add(autorizacao);
    }
}
