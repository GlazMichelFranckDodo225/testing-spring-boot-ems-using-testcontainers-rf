package com.dgmf.service.impl;

import com.dgmf.dto.StudentDtoRequest;
import com.dgmf.dto.StudentDtoResponse;
import com.dgmf.entity.Student;
import com.dgmf.mapper.StudentMapper;
import com.dgmf.repository.StudentRepository;
import com.dgmf.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDtoResponse createStudent(StudentDtoRequest studentDtoRequest) {
        Student student = studentMapper.mapToStudent(studentDtoRequest);
        Student savedStudent = studentRepository.save(student);

        return studentMapper.mapToStudentDtoResponse(savedStudent);
    }

    @Override
    public List<StudentDtoResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students
                .stream()
                .map(
                        studentMapper::mapToStudentDtoResponse
                ).collect(Collectors.toList());
    }

    @Override
    public StudentDtoResponse getStudentById(Long studentDtoRequestId) {
        Student student = studentRepository.findById(studentDtoRequestId)
                .orElseThrow(
                    () -> new RuntimeException(
                                    "Student Not Found with the Given Id " + studentDtoRequestId
                        )
                );

        return studentMapper.mapToStudentDtoResponse(student);
    }

    @Override
    public StudentDtoResponse updateStudentById(
            Long studentDtoRequestId, StudentDtoRequest studentDtoRequest
    ) {
        Student student = studentRepository.findById(studentDtoRequestId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Student Not Found with the Given Id " + studentDtoRequestId
                        )
                );

        Student updatedStudent = Student.builder()
                .firstName(studentDtoRequest.getFirstName())
                .lastName(studentDtoRequest.getLastName())
                .username(studentDtoRequest.getUsername())
                .email(studentDtoRequest.getEmail())
                .password(studentDtoRequest.getPassword())
                .build();

        Student savedStudent = studentRepository.save(updatedStudent);

        return studentMapper.mapToStudentDtoResponse(savedStudent);
    }

    @Override
    public void deleteStudentById(Long studentDtoRequestId) {
        Student student = studentRepository.findById(studentDtoRequestId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Student Not Found with the Given Id " + studentDtoRequestId
                        )
                );

        studentRepository.delete(student);
    }
}
