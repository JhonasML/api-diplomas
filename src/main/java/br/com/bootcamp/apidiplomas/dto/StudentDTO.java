package br.com.bootcamp.apidiplomas.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class StudentDTO {
    @NotBlank
    @Length(min = 8, max = 50)
    @Pattern(regexp = "([a-z]|[A-Z]|( ))*")
    private String name;

    @NotNull
    @Valid
    List<SubjectDTO> subjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }
}
