package com.dgmf.integrationtest;

import com.dgmf.entity.Student;
import com.dgmf.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/*
Will :
    - Load Repository Layer Components
    - Provide InMemory DB for Testing Purposes (Unit Tests).
*/
@DataJpaTest
// For Integration, We Have to Use Real DB (i.e MySQL). To Achieve
// this, We Have to Disable InMemory DB Support Like Below in Order to Use
// the DB Configured into "application.properties" File
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryIntegrationTests extends AbstractContainerBaseTest {
    @Autowired
    private StudentRepository studentRepository;

    // JUnit Test for Save Student Operation
    @Test
    @DisplayName("JUnit Test for Save Student Operation")
    void givenStudentObject_whenSave_thenReturnedSavedStudent() {
        // Given - Setup or Precondition
        Student student = Student.builder()
                .firstName("Kevin")
                .lastName("Portland")
                .username("kevinportland")
                .email("kevinportland@gmail.com")
                .password("123")
                .build();

        // When - Action or the Testing
        Student savedStudent = studentRepository.save(student);

        // Then - Verify the Output
        Assertions.assertNotNull(savedStudent.getId());
        Assertions.assertNotNull(savedStudent);
    }
}