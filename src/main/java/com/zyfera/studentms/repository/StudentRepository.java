package com.zyfera.studentms.repository;

import com.zyfera.studentms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
