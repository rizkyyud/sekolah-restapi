package com.sekolah.rest.api.sekolah_restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sekolah.rest.api.sekolah_restapi.helper.CustomResponse;
import com.sekolah.rest.api.sekolah_restapi.model.entity.Course;
import com.sekolah.rest.api.sekolah_restapi.service.CourseService;

@RestController
@RequestMapping("api/school/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Course>>> getAllCourse() {
        List<Course> courses = courseService.getAllCourse();
        CustomResponse<List<Course>> response = new CustomResponse<>(
                "success",
                "Course retrieved successfully",
                courses,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Course>> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        CustomResponse<Course> response = new CustomResponse<>(
                "success",
                "Course retrieved successfully",
                course,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Course>> addCourse(@RequestBody Course course) {
        Course addCourse = courseService.addCourse(course);
        CustomResponse<Course> response = new CustomResponse<>(
                "success",
                "Course add successfully",
                addCourse,
                HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Course>> updateCourse(@PathVariable Long id,
            @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        CustomResponse<Course> response = new CustomResponse<>(
                "success",
                "Course updated successfully",
                updatedCourse,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        CustomResponse<Void> response = new CustomResponse<>(
                "success",
                "Course deleted successfully",
                null,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);

    }
}
