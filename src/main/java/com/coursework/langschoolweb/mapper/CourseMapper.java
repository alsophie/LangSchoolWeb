package com.coursework.langschoolweb.mapper;

import com.coursework.langschoolweb.dto.CourseDto;
import com.coursework.langschoolweb.models.Course;

import java.util.stream.Collectors;

import static com.coursework.langschoolweb.mapper.TeacherMapper.mapToTeacherDto;

public class CourseMapper {
    public static Course mapToCourse(CourseDto course) {
        Course courseDto = Course.builder()
                .id(course.getId())
                .title(course.getTitle())
                .photoUrl(course.getPhotoUrl())
                .content(course.getContent())
                .createdOn(course.getCreatedOn())
                .updatedOn(course.getUpdatedOn())
                .build();
        return courseDto;
    }

    public static CourseDto mapToCourseDto(Course course) {
        CourseDto courseDto = CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .photoUrl(course.getPhotoUrl())
                .content(course.getContent())
                .createdOn(course.getCreatedOn())
                .updatedOn(course.getUpdatedOn())
                .teachers(course.getTeachers().stream().map((teacher) -> mapToTeacherDto(teacher)).collect(Collectors.toList()))
                .build();
        return courseDto;
    }
}
