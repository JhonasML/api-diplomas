package br.com.bootcamp.apidiplomas.dto;

public class DiplomaDTO {
    private String message;
    private Double average;
    private StudentDTO student;

    public DiplomaDTO() {
    }

    public DiplomaDTO(String message, Double average, StudentDTO student) {
        this.message = message;
        this.average = average;
        this.student = student;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }
}
