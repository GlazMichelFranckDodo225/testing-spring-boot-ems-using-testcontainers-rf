package com.dgmf.service.impl;

import com.dgmf.dto.StudentDtoRequest;
import com.dgmf.dto.StudentDtoResponse;
import com.dgmf.mapper.StudentMapper;
import com.dgmf.repository.StudentRepository;
import com.dgmf.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDtoResponse createStudent(StudentDtoRequest studentDtoRequest) {
        return null;
    }

    @Override
    public List<StudentDtoResponse> getAllStudents() {
        return List.of();
    }

    @Override
    public StudentDtoResponse getStudentById(Long studentDtoRequestId) {
        return null;
    }

    @Override
    public StudentDtoResponse updateStudentById(Long studentDtoRequestId, StudentDtoRequest studentDtoRequest) {
        return null;
    }

    @Override
    public void deleteStudentById(Long studentDtoRequestId) {

    }
}
