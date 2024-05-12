package com.dgmf.controller;

import com.dgmf.dto.StudentDtoRequest;
import com.dgmf.dto.StudentDtoResponse;
import com.dgmf.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    // Create Student REST API
    @PostMapping
    public ResponseEntity<StudentDtoResponse> createStudent(
            @RequestBody StudentDtoRequest studentDtoRequest
    ) {
        return new ResponseEntity<>(
                studentService.createStudent(studentDtoRequest),
                HttpStatus.CREATED
        );
    }

    // Get All Students REST API
    @GetMapping
    public ResponseEntity<List<StudentDtoResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // Get Student By Id REST API
    @GetMapping("/{id}")
    public ResponseEntity<StudentDtoResponse> getStudentById(
            @PathVariable("id") Long studentDtoRequestId
    ) {
        return ResponseEntity.ok(studentService.getStudentById(studentDtoRequestId));
    }

    // Update Student By Id REST API
    @PutMapping("/{id}")
    public ResponseEntity<StudentDtoResponse> updateStudentById(
            @PathVariable("id") Long studentDtoRequestId,
            @RequestBody StudentDtoRequest studentDtoRequest
    ) {
        return ResponseEntity.ok(
                studentService.updateStudentById(studentDtoRequestId, studentDtoRequest)
        );
    }

    // Delete Student By Id REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") Long studentDtoRequestId) {
        studentService.deleteStudentById(studentDtoRequestId);
        
        return ResponseEntity.ok("Student Deleted Successfully");
    }
}
