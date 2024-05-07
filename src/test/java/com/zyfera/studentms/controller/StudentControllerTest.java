package com.zyfera.studentms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyfera.studentms.entity.Grade;
import com.zyfera.studentms.entity.Student;
import com.zyfera.studentms.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@DisplayName("It should handle the controller layer operations successfully.")
public class StudentControllerTest {
    @MockBean
    private StudentService studentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

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
    @DisplayName("It should create a new student using the appropriate controller method.")
    void save() throws Exception {
        Mockito.when(studentService.createStudent(Mockito.any(Student.class))).thenReturn(Collections.singletonMap("The student is created successfully!", HttpStatus.CREATED));
        Assertions.assertEquals(mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(firstStudent)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(), "The student is created successfully!");
    }

    @Test
    @DisplayName("It should not create a new student using the appropriate controller method. It should return 404 status code.")
    void saveFailedFirstCase() throws Exception {
        Mockito.when(studentService.createStudent(Mockito.any(Student.class))).thenReturn(Collections.singletonMap("The student is not found!", HttpStatus.NOT_FOUND));
        Assertions.assertEquals(mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(firstStudent)))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString(), "The student is not found!");
    }

}
