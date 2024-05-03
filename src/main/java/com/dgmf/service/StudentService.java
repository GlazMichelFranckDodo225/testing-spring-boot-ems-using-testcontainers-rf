package com.dgmf.service;

import com.dgmf.dto.StudentDtoRequest;
import com.dgmf.dto.StudentDtoResponse;

import java.util.List;

public interface StudentService {
    StudentDtoResponse createStudent(StudentDtoRequest studentDtoRequest);
    List<StudentDtoResponse> getAllStudents();
    StudentDtoResponse getStudentById(Long studentDtoRequestId);
    StudentDtoResponse updateStudentById(
            Long studentDtoRequestId, StudentDtoRequest studentDtoRequest
    );
    void deleteStudentById(Long studentDtoRequestId);
}
