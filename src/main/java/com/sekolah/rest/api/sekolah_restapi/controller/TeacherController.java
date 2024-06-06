package com.sekolah.rest.api.sekolah_restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sekolah.rest.api.sekolah_restapi.helper.CustomResponse;
import com.sekolah.rest.api.sekolah_restapi.model.entity.Teacher;
import com.sekolah.rest.api.sekolah_restapi.service.TeacherService;

@RestController
@RequestMapping("api/school/teachers")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Teacher>>> getAllTeacher() {
        List<Teacher> teacher = teacherService.getAllTeacher();
        CustomResponse<List<Teacher>> response = new CustomResponse<>(
                "success",
                "Teacher retrieved successfully",
                teacher,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Teacher>> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        CustomResponse<Teacher> response = new CustomResponse<>(
                "success",
                "Teacher retrieved successfully",
                teacher,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Teacher>> addTeacher(@RequestBody Teacher teacher) {
        Teacher addTeacher = teacherService.addTeacher(teacher);
        CustomResponse<Teacher> response = new CustomResponse<>(
                "success",
                "Teacher add successfully",
                addTeacher,
                HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Teacher>> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        Teacher updateTeacher = teacherService.updateTeacher(id, teacher);
        CustomResponse<Teacher> response = new CustomResponse<>(
                "success",
                "Teacher update successfully",
                updateTeacher,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        CustomResponse<Void> response = new CustomResponse<>(
                "success",
                "Teacher deleted successfully",
                null,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
