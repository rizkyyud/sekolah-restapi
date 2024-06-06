package com.sekolah.rest.api.sekolah_restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekolah.rest.api.sekolah_restapi.helper.ResourceNotFoundException;
import com.sekolah.rest.api.sekolah_restapi.model.entity.Major;
import com.sekolah.rest.api.sekolah_restapi.model.repository.MajorRepository;
import com.sekolah.rest.api.sekolah_restapi.model.repository.StudentRepository;

@Service
public class MajorService {

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Major> getAllMajor() {
        return majorRepository.findAll();
    }

    public Major getMajorById(Long id) {
        return majorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Major not found"));
    }

    public Major addMajor(Major major) {
        return majorRepository.save(major);
    }

    public Major updateMajor(Long id, Major major) {
        Major existingMajor = majorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Update Failed, Major not found"));
        existingMajor.setName(major.getName());
        return majorRepository.save(existingMajor);
    }

    public void deleteMajor(Long id) {
        Major existingMajor = majorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delete Failed, Major not found"));
        if (studentRepository.existsByMajor(id)) {
            throw new IllegalStateException("Cannot delete major, it is used in student");
        }
        majorRepository.delete(existingMajor);
    }

}
