package com.sekolah.rest.api.sekolah_restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekolah.rest.api.sekolah_restapi.helper.ResourceNotFoundException;
import com.sekolah.rest.api.sekolah_restapi.model.entity.Teacher;
import com.sekolah.rest.api.sekolah_restapi.model.repository.CourseRepository;
import com.sekolah.rest.api.sekolah_restapi.model.repository.TeacherRepository;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher Not Found"));
    }

    public Teacher addTeacher(Teacher teacher) {
        if (courseRepository.findById(teacher.getCourse()).isEmpty()) {
            throw new ResourceNotFoundException("Failed Add Teacher, Course not registered");
        }
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Update Failed, Teacher not found"));
        existingTeacher.setName(teacher.getName());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setCourse(teacher.getCourse());
        return teacherRepository.save(existingTeacher);
    }

    public void deleteTeacher(Long id) {
        Teacher existingtTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delete Failed, Teacher not found"));
        teacherRepository.delete(existingtTeacher);
    }
}
