package com.sekolah.rest.api.sekolah_restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekolah.rest.api.sekolah_restapi.helper.ResourceNotFoundException;
import com.sekolah.rest.api.sekolah_restapi.model.entity.Student;
import com.sekolah.rest.api.sekolah_restapi.model.repository.MajorRepository;
import com.sekolah.rest.api.sekolah_restapi.model.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MajorRepository majorRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {

        if (majorRepository.findById(student.getMajor()).isEmpty()) {
            throw new IllegalArgumentException("Major not registered");
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Update Failed, Student not found"));
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setMajor(student.getMajor());
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delete Failed, Student not found"));
        studentRepository.delete(existingStudent);
    }
}
