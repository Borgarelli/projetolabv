package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import br.gov.sp.fatec.projetolab5.entity.Comentario;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.AnotacaoRepository;
import br.gov.sp.fatec.projetolab5.repository.ComentarioRepository;

@SpringBootTest
public class ComentarioServiceTest {

    @Autowired
    private ComentarioService service;

    @MockBean
    private AnotacaoRepository anotacaoRepo;

    @MockBean
    private ComentarioRepository comentarioRepo;

    // Teste para adicionar um novo comentário, com sucesso.
    @Test
    public void novoComentarioTestOk() {
        // Criação de objetos de exemplo
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setSenha("abc123");
        Anotacao anotacao = new Anotacao();
        anotacao.setId(1L);
        anotacao.setTexto("Anotação teste");
        anotacao.setUsuario(usuario);
        anotacao.setDataHora(new Date());
        Comentario comentario = new Comentario();
        comentario.setId(1L);
        comentario.setTexto("Comentário teste 1");
        comentario.setDataHora(new Date());
        comentario.setAnotacao(anotacao);

        // Configuração dos objetos de mock para retornar resultados esperados
        Mockito.when(anotacaoRepo.findById(1L)).thenReturn(Optional.of(anotacao));
        Mockito.when(comentarioRepo.save(any())).thenReturn(comentario);

        // Execução do método sendo testado e verificação do resultado
        assertEquals("Comentário teste 1", service.novoComentario("Comentário teste 1", 1L).getTexto());
    }

    // Teste para adicionar um novo comentário com uma anotação que não existe
    @Test
    public void novoComentarioAutorizacaoNaoExisteTestNOk() {
        // Configuração do objeto de mock para retornar um valor vazio para o findById
        Mockito.when(anotacaoRepo.findById(1L)).thenReturn(Optional.empty());

        // Execução do método sendo testado e verificação do lançamento da exceção esperada
        assertThrows(ResponseStatusException.class, () -> {
            service.novoComentario("Comentário teste", 1L);
        });
    }  
}
