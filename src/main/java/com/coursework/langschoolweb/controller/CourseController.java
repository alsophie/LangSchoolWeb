package com.coursework.langschoolweb.controller;

import com.coursework.langschoolweb.dto.CourseDto;
import com.coursework.langschoolweb.models.Course;
import com.coursework.langschoolweb.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String listCourses(Model model, Authentication authentication) {
        List<CourseDto> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        if(authentication == null) {
            return "redirect:/register";
        }
        if (isAdmin(authentication)) {
            model.addAttribute("isAdmin", true);
        }
        return "courses-list";
    }

    @GetMapping("/courses/{courseId}")
    public String courseDetail(@PathVariable("courseId") long courseId, Model model, Authentication authentication) {
        CourseDto courseDto = courseService.findCourseById(courseId);
        model.addAttribute("course", courseDto);
        if (isAdmin(authentication)) {
            model.addAttribute("isAdmin", true);
        }
        return "courses-detail";
    }

    @GetMapping("/courses/new")
    public String createCourseForm(Model model, Authentication authentication) {
        Course course = new Course();
        model.addAttribute("course", course);
        if (isAdmin(authentication)) {
            model.addAttribute("isAdmin", true);
        }
        return "courses-create";
    }

    @GetMapping("/courses/{courseId}/delete")
    public String deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.delete(courseId);
        return "redirect:/courses";
    }

    @GetMapping("/courses/search")
    public String searchCourse(@RequestParam(value = "query") String query, Model model) {
        List<CourseDto> courses = courseService.searchCourses(query);
        model.addAttribute("courses", courses);
        return "courses-list";
    }

    @PostMapping("/courses/new")
    public String saveCourse(@Valid @ModelAttribute("course") CourseDto courseDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("course", courseDto);
            return "courses-create";
        }
        courseService.saveCourse(courseDto);
        return "redirect:/courses";
    }

    @GetMapping("/courses/{courseId}/edit")
    public String editCourseForm(@PathVariable("courseId") long courseId, Model model, Authentication authentication) {
        CourseDto course = courseService.findCourseById(courseId);
        model.addAttribute("course", course);
        return "courses-edit";
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ADMIN"));
    }

    @PostMapping("/courses/{courseId}/edit")
    public String updateCourse(@PathVariable("courseId") Long courseId,
                               @Valid @ModelAttribute("course") CourseDto course,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("course", course);
            return "courses-edit";
        }
        course.setId(courseId);
        courseService.updateCourse(course);
        return "redirect:/courses";
    }

}
