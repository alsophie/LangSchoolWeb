package com.coursework.langschoolweb.repository;

import com.coursework.langschoolweb.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
