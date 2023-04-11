package br.gov.sp.fatec.projetolab5.service;
//importa asserções e bibliotecas necessárias
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.UsuarioRepository;

@SpringBootTest
public class SegurancaServiceTest {

    //Injeta o serviço de segurança
    @Autowired
    private SegurancaService service;

    //Cria um mock para o repositório de usuário
    @MockBean
    private UsuarioRepository usuarioRepo;

    //Configuração prévia dos testes
    @BeforeEach
    public void setUp() {
        //Cria um usuário para os testes
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Teste");
        usuario.setSenha("Senha");
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(usuario);
        Optional<Usuario> usuarioOp = Optional.of(usuario);
        
        //Define o comportamento do mock para o método findById
        Mockito.when(usuarioRepo.findById(1L)).thenReturn(usuarioOp);
        
        //Define o comportamento do mock para o método save
        Mockito.when(usuarioRepo.save(any())).thenReturn(usuario);
        
        //Define o comportamento do mock para o método findAll
        Mockito.when(usuarioRepo.findAll()).thenReturn(usuarios);
    }

    //Teste para buscar um usuário por ID
    @Test
    public void buscarUsuarioPorIdTestOk() {
        assertEquals("Teste", service.buscarUsuarioPorId(1L).getNome());
    }

    //Teste para buscar um usuário por ID que não existe, deve dar erro
    @Test
    public void buscarUsuarioPorIdTestNOk() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.buscarUsuarioPorId(2L);
        });
    }

    //Teste para criar um novo usuário com nome nulo, deve dar erro
    //novo Usuário nome null e vazio
    @Test
    public void novoUsuarioTestNOkNomeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
                service.novoUsuario(null, "Senha");
            });
    }

    //Teste para criar um novo usuário com nome vazio, deve dar erro
    @Test
    public void novoUsuarioTestNOkNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
                service.novoUsuario("", "Senha");
            });
    }

    //Teste para criar um novo usuário com senha nula, deve dar erro
    //novo Usuário senha null e vazio
    @Test
    public void novoUsuarioTestNOkSenhaNull() {
        assertThrows(IllegalArgumentException.class, () -> {
                service.novoUsuario("Teste", null);
            });
    }

    //Teste para criar um novo usuário com senha vazia, deve dar erro
    @Test
    public void novoUsuarioTestNOkSenhaVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
                service.novoUsuario("Teste", "");
            });
    }

    //Teste para criar um novo usuário com nome e senha válidos, não deve dar erro
    @Test
    public void novoUsuarioTestOk() {
        assertDoesNotThrow(() -> {
                service.novoUsuario("Teste", "Senha");
            });
    }


    @Test
    public void todosUsuariosTestOk() {
        assertEquals(1, service.todosUsuarios().size());
    }
    
}

