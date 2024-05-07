package com.zyfera.studentms.controller;

import com.zyfera.studentms.entity.Student;
import com.zyfera.studentms.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/students")
@AllArgsConstructor
@Slf4j
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        Map<String, HttpStatus> responseMap = studentService.createStudent(student);
        Map.Entry<String, HttpStatus> responseEntry = responseMap.entrySet().iterator().next();
        log.info("Create student operation is done in the controller layer.");
        return new ResponseEntity<>(responseEntry.getKey(), responseEntry.getValue());
    }
}
