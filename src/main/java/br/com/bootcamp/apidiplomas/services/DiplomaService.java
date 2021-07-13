package br.com.bootcamp.apidiplomas.services;

import br.com.bootcamp.apidiplomas.dto.DiplomaDTO;
import br.com.bootcamp.apidiplomas.dto.StudentDTO;
import br.com.bootcamp.apidiplomas.dto.SubjectDTO;
import org.springframework.stereotype.Service;

@Service
public class DiplomaService {
    public static final String NOTE_MESSAGE = "Sua média foi de %s";

    public DiplomaDTO analyzeNotes(StudentDTO studentDTO) {
        double mediaNotas = studentDTO.getSubjects()
                                        .stream()
                                        .mapToDouble(SubjectDTO::getNote)
                                        .average()
                                        .orElse(0);

        return new DiplomaDTO(String.format(NOTE_MESSAGE, mediaNotas), mediaNotas, studentDTO);
    }
}
