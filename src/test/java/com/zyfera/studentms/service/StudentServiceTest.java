package com.zyfera.studentms.service;

import com.zyfera.studentms.entity.Grade;
import com.zyfera.studentms.entity.Student;
import com.zyfera.studentms.repository.StudentRepository;
import com.zyfera.studentms.service.impl.StudentServiceImpl;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
@DisplayName("It should handle the service layer operations successfully.")
public class StudentServiceTest {
    @InjectMocks
    private StudentServiceImpl studentService;
    @Mock
    private Validator validator;
    @Mock
    private StudentRepository studentRepository;

    private Student firstStudent;
    private List<Grade> grades;

    @BeforeEach
    @DisplayName("Setting up the data for the test cases.")
    void init() {
        firstStudent = new Student();
        firstStudent.setId(1L);
        firstStudent.setName("Ali");
        firstStudent.setSurname("Yilmaz");
        firstStudent.setStdNumber("B012X00012");
        grades = new ArrayList<>();
        Grade firstGrade = new Grade(1L, "MT101", 90, null);
        Grade secondGrade = new Grade(2L, "PH101", 75, null);
        Grade thirdGrade = new Grade(3L, "CH101", 60, null);
        Grade fourthGrade = new Grade(4L, "MT101", 70, null);
        Grade fifthGrade = new Grade(5L, "HS101", 65, null);
        grades.add(firstGrade);
        grades.add(secondGrade);
        grades.add(thirdGrade);
        grades.add(fourthGrade);
        grades.add(fifthGrade);
        firstStudent.setGrades(grades);
    }

    @Test
    @DisplayName("The job service should save the job to the database and return success message with 201 status code.")
    void save() {
        Map<String, HttpStatus> statusMap = studentService.createStudent(firstStudent);
        Assertions.assertEquals(statusMap, Collections.singletonMap("The student is created successfully!", HttpStatus.CREATED));
    }

    @Test
    @DisplayName("The job service should not save the job to the database and return job is not found message with 404 status code.")
    void tryToSaveNullJob() {
        Map<String, HttpStatus> statusMap = studentService.createStudent(null);
        Assertions.assertEquals(statusMap, Collections.singletonMap("The student is not valid!", HttpStatus.BAD_REQUEST));
    }
}
