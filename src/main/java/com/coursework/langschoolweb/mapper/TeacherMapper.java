package com.coursework.langschoolweb.mapper;

import com.coursework.langschoolweb.dto.TeacherDto;
import com.coursework.langschoolweb.models.Teacher;

public class TeacherMapper {
    public static Teacher mapToTeacher(TeacherDto teacherDto) {
        return Teacher.builder()
                .id(teacherDto.getId())
                .fullName(teacherDto.getFullName())
                .content(teacherDto.getContent())
                .experience(teacherDto.getExperience())
                .photoUrl(teacherDto.getPhotoUrl())
                .createdOn(teacherDto.getCreatedOn())
                .updatedOn(teacherDto.getUpdatedOn())
                .course(teacherDto.getCourse())
                .build();
    }

    public static TeacherDto mapToTeacherDto(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .fullName(teacher.getFullName())
                .content(teacher.getContent())
                .experience(teacher.getExperience())
                .photoUrl(teacher.getPhotoUrl())
                .createdOn(teacher.getCreatedOn())
                .updatedOn(teacher.getUpdatedOn())
                .course(teacher.getCourse())
                .build();
    }
}
