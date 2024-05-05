package com.zyfera.studentms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @NotNull(message = "Please provide a valid name, student surname should not be null.")
    @NotBlank(message = "Please provide a valid name, student surname should not be blank.")
    @Size(max = 10, message = "Grade code must be at most 10 characters.")
    private String code;
    @NotNull(message = "Please provide a valid name, student surname should not be null.")
    @Min(value = 0, message = "The grade should be greater than or equal to 0.")
    @Max(value = 100, message = "The grade should be less than or equal to 100.")
    private Integer value;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Integer getGrade() {
        return value;
    }

    public String getCode() {
        return code;
    }

    public void setGrade(Integer value) {
        this.value= value;
    }
}
