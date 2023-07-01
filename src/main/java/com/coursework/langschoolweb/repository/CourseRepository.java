package com.coursework.langschoolweb.repository;

import com.coursework.langschoolweb.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
     Optional<Course> findByTitle(String url);

    @Query("SELECT c from Course c WHERE c.title LIKE CONCAT('%', :query, '%')")
    List<Course> searchCourses(String query);
}
