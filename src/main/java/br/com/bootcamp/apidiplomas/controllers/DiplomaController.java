package br.com.bootcamp.apidiplomas.controllers;

import br.com.bootcamp.apidiplomas.dto.DiplomaDTO;
import br.com.bootcamp.apidiplomas.dto.StudentDTO;
import br.com.bootcamp.apidiplomas.services.DiplomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
 public class DiplomaController {

    private final DiplomaService diplomaService;

    @Autowired
    public DiplomaController(DiplomaService diplomaService) {
        this.diplomaService = diplomaService;
    }

    @PostMapping(path = "/analyzeNotes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DiplomaDTO> analyzeNotes(@RequestBody @Valid StudentDTO studentDTO) {
        DiplomaDTO notes = diplomaService.analyzeNotes(studentDTO);
        return new ResponseEntity<>(notes, HttpStatus.CREATED);
    }
}
