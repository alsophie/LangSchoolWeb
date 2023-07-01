package com.coursework.langschoolweb.service;

import com.coursework.langschoolweb.dto.CourseDto;
import com.coursework.langschoolweb.dto.TeacherDto;

public interface TeacherService {
    void createTeacher(Long courseId, TeacherDto teacher);

    TeacherDto findTeacherById(long teacherId);

    void updateTeacher(TeacherDto teacherDto);

    void deleteTeacher(long teacherId);
}
