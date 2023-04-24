package br.gov.sp.fatec.projetolab5.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Anotação que configura o teste para ser executado com o Spring Boot
@AutoConfigureMockMvc // Anotação que configura o MockMvc, usado para simular requisições HTTP
public class ComentarioControllerIntegrationTest {

    @Autowired
    private MockMvc mvc; // Objeto que será usado para simular as requisições HTTP

    // Teste que verifica se a busca por um comentário existente retorna o resultado esperado
    @Test
    public void buscarPeloIdTestOk() throws Exception {
        mvc.perform(get("/comentario/id/{1}", 1L) // Simula uma requisição GET para o endpoint /comentario/id/1
            .accept(MediaType.APPLICATION_JSON)) // Configura o tipo de retorno esperado
            .andDo(print()) // Imprime o resultado da requisição no console
            .andExpect(status().isOk()) // Verifica se a resposta HTTP tem status 200 (OK)
            .andExpect(jsonPath("$.texto").value("Essa anotação me ajudou muito na prova")); // Verifica se o texto do comentário retornado é o esperado
    }

    // Teste que verifica se a busca por um comentário inexistente retorna o resultado esperado
    @Test
    public void buscarPeloIdTestNOk() throws Exception {
        mvc.perform(get("/comentario/id/{1}", 2L) // Simula uma requisição GET para o endpoint /comentario/id/2
            .accept(MediaType.APPLICATION_JSON)) // Configura o tipo de retorno esperado
            .andDo(print()) // Imprime o resultado da requisição no console
            .andExpect(status().isNotFound()); // Verifica se a resposta HTTP tem status 404 (NOT FOUND)
    }
}
