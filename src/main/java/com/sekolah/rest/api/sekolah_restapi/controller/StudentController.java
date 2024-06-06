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
import com.sekolah.rest.api.sekolah_restapi.helper.ResourceNotFoundException;
import com.sekolah.rest.api.sekolah_restapi.model.entity.Student;
import com.sekolah.rest.api.sekolah_restapi.service.StudentService;

@RestController
@RequestMapping("api/school/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        CustomResponse<List<Student>> response = new CustomResponse<>(
                "success",
                "Student retrieved successfully",
                students,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Student>> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        CustomResponse<Student> response = new CustomResponse<>(
                "success",
                "Student retrieved successfully",
                student,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Student>> addStudent(@RequestBody Student student) {
        try {
            Student addStudent = studentService.addStudent(student);
            CustomResponse<Student> response = new CustomResponse<>(
                    "success",
                    "Student add successfully",
                    addStudent,
                    HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            CustomResponse<Student> response = new CustomResponse<>(
                    "error add",
                    e.getMessage(),
                    null,
                    HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Student>> updateStudent(@PathVariable Long id,
            @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        CustomResponse<Student> response = new CustomResponse<>(
                "success",
                "Student updated successfully",
                updatedStudent,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        CustomResponse<Void> response = new CustomResponse<>(
                "success",
                "Student deleted successfully",
                null,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

}
