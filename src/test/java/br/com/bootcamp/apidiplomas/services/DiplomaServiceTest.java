package br.com.bootcamp.apidiplomas.services;

import br.com.bootcamp.apidiplomas.dto.DiplomaDTO;
import br.com.bootcamp.apidiplomas.dto.StudentDTO;
import br.com.bootcamp.apidiplomas.dto.SubjectDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DiplomaServiceTest {

    @Test
    void analyzeNotes_success() {
        //given
        var diplomaService = new DiplomaService();
        var student = new StudentDTO("João", Arrays.asList(new SubjectDTO("Fisica", 8.0),
                new SubjectDTO("Matematica", 6.0)));

        //when
        DiplomaDTO diplomaDTO = diplomaService.analyzeNotes(student);
        //then
        assertNotNull(diplomaDTO);
        assertEquals(7.0, diplomaDTO.getAverage());
        assertEquals("Sua média foi de 7.0", diplomaDTO.getMessage());
        assertEquals(student, diplomaDTO.getStudent());
    }

    @Test
    void analyzeNotes_withoutSubjects_success() {
        //given
        var diplomaService = new DiplomaService();
        var student = new StudentDTO("João", new ArrayList<>());

        //when
        DiplomaDTO diplomaDTO = diplomaService.analyzeNotes(student);
        //then
        assertNotNull(diplomaDTO);
        assertNotNull(diplomaDTO);
        assertEquals(0.0, diplomaDTO.getAverage());
        assertEquals("Sua média foi de 0.0", diplomaDTO.getMessage());
        assertEquals(student, diplomaDTO.getStudent());
    }


    @Test
    void analyzeNotes_withNullSubjects_nullPointer() {
        //given
        var diplomaService = new DiplomaService();
        var student = new StudentDTO("João", null);

        //when
        assertThrows(NullPointerException.class, () -> {
            diplomaService.analyzeNotes(student);
        });
    }
}