package com.zyfera.studentms.repository;

import com.zyfera.studentms.entity.Grade;
import com.zyfera.studentms.entity.Student;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@DisplayName("It should handle the repository operations successfully.")
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    private Student firstStudent;
    private List<Grade> grades;

    @BeforeEach
    @DisplayName("Setting up the data for the test cases.")
    void init() {
        firstStudent = new Student();
        firstStudent.setName("Ali");
        firstStudent.setSurname("Yilmaz");
        firstStudent.setStdNumber("B012X00012");
        grades = new ArrayList<>();
        Grade firstGrade = new Grade(null, "MT101", 90, firstStudent);
        Grade secondGrade = new Grade(null, "PH101", 75, firstStudent);
        Grade thirdGrade = new Grade(null, "CH101", 60, firstStudent);
        Grade fourthGrade = new Grade(null, "MT101", 70, firstStudent);
        Grade fifthGrade = new Grade(null, "HS101", 65, firstStudent);
        grades.add(firstGrade);
        grades.add(secondGrade);
        grades.add(thirdGrade);
        grades.add(fourthGrade);
        grades.add(fifthGrade);
        firstStudent.setGrades(grades);
    }

    @Test
    @DisplayName("It should save the student to the database.")
    void save() {
        Student savedStudent = studentRepository.save(firstStudent);
        Assertions.assertAll("Student Saved In Database",
                () -> Assertions.assertNotNull(savedStudent),
                () -> Assertions.assertNotSame(firstStudent.getId(), null));
    }
}
