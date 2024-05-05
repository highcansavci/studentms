package com.zyfera.studentms.service.impl;

import com.zyfera.studentms.entity.Grade;
import com.zyfera.studentms.entity.Student;
import com.zyfera.studentms.repository.StudentRepository;
import com.zyfera.studentms.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    private StudentRepository studentRepository;
    @Override
    public Map<String, HttpStatus> createStudent(Student student) {
        if(student == null) {
            log.error("The job is not valid upon the creation in the service layer!");
            return Collections.singletonMap("The student is not valid!", HttpStatus.BAD_REQUEST);
        }
        List<Grade> gradeList = student.getGrades();
        // Find the average grades for every grade code and group by.
        Map<String, Double> averagedGrades = gradeList.stream().collect(Collectors.groupingBy(grade -> grade.getCode(), Collectors.averagingInt(grade -> grade.getGrade().intValue())));
        // Update the grades for every grade code.
        gradeList.forEach(grade -> grade.setGrade(averagedGrades.get(grade.getCode()).intValue()));
        // Handle duplicates gracefully.
        student.setGrades(gradeList.stream().distinct().toList());
        studentRepository.save(student);
        log.info("The student is created successfully in the service layer!");
        return Collections.singletonMap("The student is created successfully", HttpStatus.CREATED);
    }

}
