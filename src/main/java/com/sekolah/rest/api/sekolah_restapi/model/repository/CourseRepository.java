package com.sekolah.rest.api.sekolah_restapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sekolah.rest.api.sekolah_restapi.model.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
