package com.zyfera.studentms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @NotNull(message = "Please provide a valid grade code, grade code should not be null.")
    @NotBlank(message = "Please provide a valid grade code, grade code should not be blank.")
    @Size(max = 10, message = "Grade code must be at most 10 characters.")
    private String code;
    @NotNull(message = "Please provide a valid grade, student grade should not be null.")
    @Min(value = 0, message = "The grade should be greater than or equal to 0.")
    @Max(value = 100, message = "The grade should be less than or equal to 100.")
    private Integer gradeValue;
    @ManyToOne
    @JoinColumn(name = "student_id")
    @Valid
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;
}
