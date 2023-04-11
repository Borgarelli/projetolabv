package br.gov.sp.fatec.projetolab5.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //define que esta é uma classe de teste do Spring
@AutoConfigureMockMvc //configura automaticamente a injeção de dependência do MockMvc
public class UsuarioControllerIntegrationTest {
    
    @Autowired //indica que o campo abaixo será injetado pelo Spring
    private MockMvc mvc;

    @Test //indica que o método abaixo é um teste
    public void novoUsuarioTestOk() throws Exception {
        mvc.perform(post("/usuario") //faz uma requisição POST para o endpoint "/usuario"
            .content("{\"nome\":\"TesteMvc\", \"senha\":\"senha\"}") //define o corpo da requisição como um objeto JSON
            .contentType(MediaType.APPLICATION_JSON) //define o tipo de mídia da requisição como JSON
            .accept(MediaType.APPLICATION_JSON)) //define o tipo de mídia da resposta esperada como JSON
            .andDo(print()) //imprime o resultado da requisição no console
            .andExpect(status().isOk()) //verifica se o status da resposta é OK (200)
            .andExpect(jsonPath("$.id").exists()); //verifica se o JSON de resposta possui a chave "id"
    }

    @Test //indica que o método abaixo é um teste
    public void buscarPeloIdTestOk() throws Exception {
        mvc.perform(get("/usuario/{1}", 1L) //faz uma requisição GET para o endpoint "/usuario/{id}"
            .accept(MediaType.APPLICATION_JSON)) //define o tipo de mídia da resposta esperada como JSON
            .andDo(print()) //imprime o resultado da requisição no console
            .andExpect(status().isOk()) //verifica se o status da resposta é OK (200)
            .andExpect(jsonPath("$.nome").value("admin")); //verifica se o JSON de resposta possui a chave "nome" e seu valor é "admin"
    }

}
