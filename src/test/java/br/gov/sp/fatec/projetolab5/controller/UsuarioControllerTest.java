package br.gov.sp.fatec.projetolab5.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.service.SegurancaService;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc; // Injetando o MockMvc para realizar as chamadas HTTP

    @MockBean
    private SegurancaService service; // Criando um mock do serviço que será usado no controller

    @Test
    public void novoUsuarioTestOk() throws Exception {
        Usuario usuario = new Usuario("Teste", "senha");
        usuario.setId(1L);
        Mockito.when(service.novoUsuario(any())).thenReturn(usuario); // Configurando o comportamento do mock

        mvc.perform(post("/usuario") // Realizando uma requisição HTTP do tipo POST para a rota /usuario
            .content("{\"nome\":\"TesteMvc\", \"senha\":\"senha\"}") // Configurando o corpo da requisição
            .contentType(MediaType.APPLICATION_JSON)) // Configurando o tipo de conteúdo da requisição
            .andDo(print()) // Imprimindo o resultado da requisição no console
            .andExpect(status().isOk()) // Verificando se o status da resposta é 200 (OK)
            .andExpect(jsonPath("$.id").value(1L)); // Verificando se a resposta contém o atributo "id" com o valor 1
    }

    @Test
    public void listarUsuarioOk() throws Exception {
        Usuario usuario = new Usuario("Teste", "senha");
        usuario.setId(1L);
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios.add(usuario);

        Mockito.when(service.todosUsuarios()).thenReturn(usuarios); // Configurando o comportamento do mock

        mvc.perform(get("/usuario")) // Realizando uma requisição HTTP do tipo GET para a rota /usuario
            .andExpect(status().isOk()) // Verificando se o status da resposta é 200 (OK)
            .andExpect(jsonPath("$.length()").value(1)) // Verificando se a resposta contém um array com um elemento
            .andExpect(jsonPath("$[0].id").value(1L)) // Verificando se o primeiro elemento do array contém o atributo "id" com o valor 1
        ;
    }
    
}
