package br.com.bootcamp.apidiplomas.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class SubjectDTO {

    @NotNull
    @Length(min = 8, max =50)
    @Pattern(regexp = "[a-zA-Z ]*")
    private String name;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double note;

    public SubjectDTO(String name, Double note) {
        this.name = name;
        this.note = note;
    }

    public SubjectDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
}
