package com.coursework.langschoolweb.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CourseDto {
    private Long id;
    @NotEmpty(message = "Course shouldn't be empty")
    private String title;
    @NotEmpty(message = "Photo Link shouldn't be empty")
    private String photoUrl;
    @NotEmpty(message = "Content shouldn't be empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<TeacherDto> teachers;
}
