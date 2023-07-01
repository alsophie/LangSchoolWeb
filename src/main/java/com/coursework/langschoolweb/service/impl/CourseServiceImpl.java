package com.coursework.langschoolweb.service.impl;

import com.coursework.langschoolweb.dto.CourseDto;
import com.coursework.langschoolweb.models.Course;
import com.coursework.langschoolweb.repository.CourseRepository;
import com.coursework.langschoolweb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.coursework.langschoolweb.mapper.CourseMapper.mapToCourse;
import static com.coursework.langschoolweb.mapper.CourseMapper.mapToCourseDto;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDto> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map((course) -> mapToCourseDto(course)).collect(Collectors.toList());
    }

    @Override
    public Course saveCourse(CourseDto courseDto) {
        Course course = mapToCourse(courseDto);
        return courseRepository.save(course);
    }

    @Override
    public CourseDto findCourseById(long courseId) {
        Course course = courseRepository.findById(courseId).get();
        return mapToCourseDto(course);
    }

    @Override
    public void updateCourse(CourseDto courseDto) {
        Course course = mapToCourse(courseDto);
        courseRepository.save(course);
    }

    @Override
    public void delete(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<CourseDto> searchCourses(String query) {
        List<Course> courses = courseRepository.searchCourses(query);
        return courses.stream().map(course -> mapToCourseDto(course)).collect(Collectors.toList());
    }


}
