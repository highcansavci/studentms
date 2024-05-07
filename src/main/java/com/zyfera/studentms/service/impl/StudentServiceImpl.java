package com.zyfera.studentms.service.impl;

import com.zyfera.studentms.entity.Grade;
import com.zyfera.studentms.entity.Student;
import com.zyfera.studentms.repository.StudentRepository;
import com.zyfera.studentms.service.StudentService;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    private Validator validator;
    @Override
    @Transactional
    public Map<String, HttpStatus> createStudent(Student student) {
        if(student == null) {
            log.error("The job is not valid upon the creation in the service layer!");
            return Collections.singletonMap("The student is not valid!", HttpStatus.BAD_REQUEST);
        }
        List<Grade> gradeList = student.getGrades();
        // Grades Validation
        Set<ConstraintViolation<Grade>> gradeViolationSet = gradeList.stream().map(grade -> validator.validate(grade)).flatMap(Collection::stream).collect(Collectors.toSet());
        if(!gradeViolationSet.isEmpty()) {
            throw new ConstraintViolationException(gradeViolationSet);
        }
        // If all the grades are valid, find the average grades for every grade code and group by.
        Map<String, Double> averagedGrades = gradeList.stream().collect(Collectors.groupingBy(Grade::getCode, Collectors.averagingInt(Grade::getGradeValue)));
        // Update the grades for every grade code.
        gradeList.forEach(grade -> grade.setGradeValue(averagedGrades.get(grade.getCode()).intValue()));
        // Handle duplicates gracefully.
        student.setGrades(gradeList.stream().distinct().toList());
        studentRepository.save(student);
        log.info("The student is created successfully in the service layer!");
        return Collections.singletonMap("The student is created successfully!", HttpStatus.CREATED);
    }

}
