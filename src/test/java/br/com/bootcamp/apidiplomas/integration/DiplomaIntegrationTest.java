package br.com.bootcamp.apidiplomas.integration;

import br.com.bootcamp.apidiplomas.controllers.DiplomaController;
import br.com.bootcamp.apidiplomas.dto.DiplomaDTO;
import br.com.bootcamp.apidiplomas.dto.StudentDTO;
import br.com.bootcamp.apidiplomas.dto.SubjectDTO;
import br.com.bootcamp.apidiplomas.services.DiplomaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DiplomaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void analyzeNotes_success() throws Exception {
        //given
        var student = new StudentDTO("Joaozinho da Silva", Arrays.asList(new SubjectDTO("Fisica Quantica", 5.0),
                new SubjectDTO("Quimica Aplicada", 8.0)));

        //when
        mockMvc.perform(post("/analyzeNotes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(student)))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.average", is(6.5)));
    }


    @Test
    public void analyzeNotes_withNullSubjects_error() throws Exception {
        //given
        var student = new StudentDTO("Joaozinho da Silva", null);

        //when
        mockMvc.perform(post("/analyzeNotes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(student)))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("subjects: must not be null")));
    }

    @Test
    public void analyzeNotes_withEmptySubjects_error() throws Exception {
        //given
        var student = new StudentDTO("Joaozinho da Silva", new ArrayList<>());

        //when
        mockMvc.perform(post("/analyzeNotes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(student)))
                //then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("subjects: must not be null")));
    }

    @Test
    public void analyzeNotes_notesZero_success() throws Exception {
        //given
        var student = new StudentDTO("Joaozinho da Silva", Arrays.asList(new SubjectDTO("Fisica Quantica", 0.0),
                new SubjectDTO("Quimica Aplicada", 0.0)));

        //when
        mockMvc.perform(post("/analyzeNotes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(student)))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.average", is(0.0)));
    }


}
