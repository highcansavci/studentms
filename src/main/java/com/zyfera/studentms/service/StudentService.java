package com.zyfera.studentms.service;

import com.zyfera.studentms.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface StudentService {
    Map<String, HttpStatus> createStudent(Student student);
}
