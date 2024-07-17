package org.brisa.controllers;

import com.google.gson.Gson;
import org.brisa.domain.Funcionario;
import org.brisa.repositories.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Test
    public void substituirFuncionarioTest() throws Exception {
        UUID id = UUID.randomUUID();

        Funcionario existingFuncionario = new Funcionario("Old Name", "Old Surname", "Old Role");
        existingFuncionario.setId(id);

        Funcionario updatedFuncionario = new Funcionario("New Name", "New Surname", "New Role");
        updatedFuncionario.setId(id);

        Mockito.when(funcionarioRepository.findById(id)).thenReturn(Optional.of(existingFuncionario));
        Mockito.when(funcionarioRepository.save(Mockito.any(Funcionario.class))).thenReturn(updatedFuncionario);

        MvcResult mvcResult = mockMvc.perform(put("/funcionarios/" + id.toString()).content(
                "{ \"nome\": \"New Name\", \"sobrenome\": \"New Surname\", \"papel\": \"New Role\" }"
        )).andExpect(status().is2xxSuccessful()).andReturn();

        Funcionario resultFuncionario = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), Funcionario.class);

        assertEquals(updatedFuncionario.getId(), resultFuncionario.getId());
        assertEquals(updatedFuncionario.getNome(), resultFuncionario.getNome());
        assertEquals(updatedFuncionario.getSobrenome(), resultFuncionario.getSobrenome());
        assertEquals(updatedFuncionario.getPapel(), resultFuncionario.getPapel());

        Mockito.verify(funcionarioRepository, Mockito.times(1)).findById(id);
        Mockito.verify(funcionarioRepository, Mockito.times(1)).save(updatedFuncionario);
    }

    @Test
    void substituirFuncionarioNotFoundExceptionTest() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(put("/funcionarios/" + id.toString()).content(
                "{ \"nome\": \"Name\", \"sobrenome\": \"Surname\", \"papel\": \"Role\" }"
        )).andExpect(status().is4xxClientError());
        Mockito.verify(funcionarioRepository, Mockito.times(1)).findById(id);
        Mockito.verify(funcionarioRepository, Mockito.times(0)).save(Mockito.any(Funcionario.class));
    }
}