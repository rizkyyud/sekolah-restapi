package com.sekolah.rest.api.sekolah_restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekolah.rest.api.sekolah_restapi.helper.ResourceNotFoundException;
import com.sekolah.rest.api.sekolah_restapi.model.entity.Course;
import com.sekolah.rest.api.sekolah_restapi.model.repository.CourseRepository;
import com.sekolah.rest.api.sekolah_restapi.model.repository.TeacherRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Update Failed, Course not found"));
        existingCourse.setName(course.getName());
        existingCourse.setCredits(course.getCredits());
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delete Failed, Course not found"));
        if (teacherRepository.existsByCourse(id)) {
            throw new ResourceNotFoundException("Cannot delete course, it is used in teacher");
        }
        courseRepository.delete(existingCourse);
    }
}
