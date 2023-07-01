package com.coursework.langschoolweb.service.impl;

import com.coursework.langschoolweb.dto.TeacherDto;
import com.coursework.langschoolweb.models.Course;
import com.coursework.langschoolweb.models.Teacher;
import com.coursework.langschoolweb.repository.CourseRepository;
import com.coursework.langschoolweb.repository.TeacherRepository;
import com.coursework.langschoolweb.service.CourseService;
import com.coursework.langschoolweb.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.coursework.langschoolweb.mapper.CourseMapper.mapToCourse;
import static com.coursework.langschoolweb.mapper.CourseMapper.mapToCourseDto;
import static com.coursework.langschoolweb.mapper.TeacherMapper.mapToTeacher;
import static com.coursework.langschoolweb.mapper.TeacherMapper.mapToTeacherDto;

@Service
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void createTeacher(Long courseId, TeacherDto teacherDto) {
        Course course = courseRepository.findById(courseId).get();
        Teacher teacher = mapToTeacher(teacherDto);
        teacher.setCourse(course);
        teacherRepository.save(teacher);
    }

    @Override
    public TeacherDto findTeacherById(long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).get();
        return mapToTeacherDto(teacher);
    }

    @Override
    public void updateTeacher(TeacherDto teacherDto) {
        Teacher teacher = mapToTeacher(teacherDto);
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

}
