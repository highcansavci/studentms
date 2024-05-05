package com.zyfera.studentms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Long id;
    @NotNull(message = "Please provide a valid name, student name should not be null.")
    @NotBlank(message = "Please provide a valid name, student name should not be blank.")
    @Size(max = 20, message = "Student name must be at most 20 characters.")
    private String name;
    @NotNull(message = "Please provide a valid name, student surname should not be null.")
    @NotBlank(message = "Please provide a valid name, student surname should not be blank.")
    @Size(max = 20, message = "Student surname must be at most 20 characters.")
    private String surname;
    @NotNull(message = "Please provide a valid name, student number should not be null.")
    @NotBlank(message = "Please provide a valid name, student number should not be blank.")
    @Size(max = 10, message = "Student number must be at most 10 characters.")
    private String stdNumber;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades;

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
