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
import com.sekolah.rest.api.sekolah_restapi.model.entity.Major;
import com.sekolah.rest.api.sekolah_restapi.service.MajorService;

@RestController
@RequestMapping("api/school/majors")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Major>>> getAllMajor() {
        List<Major> major = majorService.getAllMajor();
        CustomResponse<List<Major>> response = new CustomResponse<>(
                "success",
                "Major retrieved successfully",
                major,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Major>> getMajorById(@PathVariable Long id) {
        Major major = majorService.getMajorById(id);
        CustomResponse<Major> response = new CustomResponse<>(
                "success",
                "Major retrieved successfully",
                major,
                HttpStatus.CREATED.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Major>> addMajor(@RequestBody Major major) {
        Major addMajor = majorService.addMajor(major);
        CustomResponse<Major> response = new CustomResponse<>(
                "success",
                "Major add successfully",
                addMajor,
                HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Major>> updateMajor(@PathVariable Long id, @RequestBody Major major) {
        Major updateMajor = majorService.updateMajor(id, major);
        CustomResponse<Major> response = new CustomResponse<>(
                "success",
                "Major update successfully",
                updateMajor,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteMajor(@PathVariable Long id) {
        try {
            majorService.deleteMajor(id);
            CustomResponse<Void> response = new CustomResponse<>(
                    "success",
                    "Major deleted successfully",
                    null,
                    HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            CustomResponse<Void> response = new CustomResponse<>(
                    "error delete",
                    e.getMessage(),
                    null,
                    HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(response);
        }
    }

}
