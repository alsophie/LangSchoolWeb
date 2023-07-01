package com.coursework.langschoolweb.controller;

import com.coursework.langschoolweb.dto.CourseDto;
import com.coursework.langschoolweb.dto.TeacherDto;
import com.coursework.langschoolweb.models.Teacher;
import com.coursework.langschoolweb.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers/{courseId}/new")
    public String createTeacherForm(@PathVariable("courseId") Long courseId, Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("courseId", courseId);
        model.addAttribute("teacher", teacher);
        return "teachers-create";
    }

    @GetMapping("/teachers/{teacherId}/edit")
    public String editTeacherForm(@PathVariable("teacherId") long teacherId, Model model) {
        TeacherDto teacher = teacherService.findTeacherById(teacherId);
        model.addAttribute("teacher", teacher);
        return "teachers-edit";
    }

    @PostMapping("/teachers/{courseId}")
    public String addTeacher(@PathVariable("courseId") Long courseId, @ModelAttribute("teacher") TeacherDto teacherDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("teacher", teacherDto);
            return "teachers-create";
        }
        teacherService.createTeacher(courseId, teacherDto);
        return "redirect:/courses/" + courseId;
    }

    @PostMapping("/teachers/{teacherId}/edit")
    public String updateTeacher(@PathVariable("teacherId") Long teacherId,
                               @Valid @ModelAttribute("teacher") TeacherDto teacher,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("teacher", teacher);
            return "teachers-edit";
        }
        TeacherDto teacherDto = teacherService.findTeacherById(teacherId);
        teacher.setId(teacherId);
        teacher.setCourse(teacherDto.getCourse());
        teacherService.updateTeacher(teacher);
        return "redirect:/courses/" + teacherDto.getCourse().getId();
    }

    @GetMapping("/teachers/{teacherId}/delete")
    public String deleteTeacher(@PathVariable("teacherId") long teacherId, @ModelAttribute("teacher") TeacherDto teacher) {
        TeacherDto teacherDto = teacherService.findTeacherById(teacherId);
        teacherService.deleteTeacher(teacherId);
        return "redirect:/courses/" + teacherDto.getCourse().getId();
    }

}
