package com.dgmf.mapper;

import com.dgmf.dto.StudentDtoRequest;
import com.dgmf.dto.StudentDtoResponse;
import com.dgmf.entity.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {
    private final ModelMapper modelMapper;

    public StudentDtoResponse mapToStudentDtoResponse(Student student) {
        return modelMapper.map(student, StudentDtoResponse.class);
    }

    public Student mapToStudent(StudentDtoRequest studentDtoRequest) {
        return modelMapper.map(studentDtoRequest, Student.class);
    }
}
