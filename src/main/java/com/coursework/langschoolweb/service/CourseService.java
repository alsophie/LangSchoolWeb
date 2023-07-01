package com.coursework.langschoolweb.service;

import com.coursework.langschoolweb.dto.CourseDto;
import com.coursework.langschoolweb.models.Course;

import java.util.List;

public interface CourseService {
    List<CourseDto> findAllCourses();

    Course saveCourse(CourseDto courseDto);

    CourseDto findCourseById(long courseId);

    void updateCourse(CourseDto course);

    void delete(Long courseId);
    List<CourseDto> searchCourses(String query);
}
