package br.gov.sp.fatec.projetolab5.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.gov.sp.fatec.projetolab5.entity.Usuario;

@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Test
    public void novoUsuarioTest() {
        Usuario usuario = new Usuario(); // Cria uma instância da classe Usuario
        usuario.setNome("UsuarioTeste"); // Define o nome do usuário
        usuario.setSenha("123"); // Define a senha do usuário
        usuario = usuarioRepo.save(usuario); // Salva o usuário no banco de dados
        assertNotNull(usuario.getId()); // Testa se o ID do usuário não é nulo
    }
    
}
