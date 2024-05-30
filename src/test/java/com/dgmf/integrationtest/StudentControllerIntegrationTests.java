package com.dgmf.integrationtest;

import com.dgmf.dto.StudentDtoRequest;
import com.dgmf.entity.Student;
import com.dgmf.mapper.StudentMapper;
import com.dgmf.repository.StudentRepository;
import com.dgmf.service.StudentService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

// Loads Full ApplicationContext (All the Spring Beans Available into Application)
// Will Invoke Automatically the "Main" Entry Point Class (that is Deployed with
// Embedded Tomcat Server
@SpringBootTest
// To Mock MVC Library
@AutoConfigureMockMvc
// Provide Extension to Integrate JUnit 5 with Test
// Containers ==> Ctrl + B ==> @ExtendWith({TestcontainersExtension.class})
// Testcontainers will Pull Docker Image from DockerHub and Deploy It into
// Docker Container. We don't Have to Install MySQL into Local Machine, Just
// Install Docker
// @Testcontainers ==> Commented Because We Explicitly Start the Container with the Abstract Class
class StudentControllerIntegrationTests extends AbstractContainerBaseTest{
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MockMvc mockMvc;
    // @Container ==> To Make MySQLContainer Instance Life Cycle to Be
    // Managed by @Testcontainers (above) ==> Basically, Start and Stop the
    // Docker Container
    // Static ==> To Be Shared with Multiple Test Cases
    /*@Container ==> Commented Because We Explicitly Start the Container with the Abstract Class
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");*/



    // given/when/then format ==> BDD (Behavior Driven Development) Style
    // Get All Students Integration Test
    @Test
    @DisplayName("Get All Students Integration Test")
    public void givenStudents_whenGetAllStudents_thenReturnListOfStudents() throws Exception {
        // To Display Default "username" and "password" of MySQL Container into the Console.
        // They are Used by Testcontainers to Managed Embedded MySQL Database
        /*System.out.println(mySQLContainer.getJdbcUrl());
        System.out.println(mySQLContainer.getDatabaseName());
        System.out.println(mySQLContainer.getUsername());
        System.out.println(mySQLContainer.getPassword());*/

        // Given - Setup or Precondition
        List<StudentDtoRequest> studentDtos = List.of(
                StudentDtoRequest.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .username("johnDoe")
                        .email("johndoe@gmail.com")
                        .password("123")
                        .build(),
                StudentDtoRequest.builder()
                        .firstName("Tony")
                        .lastName("Stark")
                        .username("tonyStark")
                        .email("tonystark@gmail.com")
                        .password("1234")
                        .build(),
                StudentDtoRequest.builder()
                        .firstName("Maeva")
                        .lastName("Devon")
                        .username("maevadevon")
                        .email("johndoemaevadevon@gmail.com")
                        .password("12345")
                        .build(),
                StudentDtoRequest.builder()
                        .firstName("Henry")
                        .lastName("Byron")
                        .username("henrybyron")
                        .email("henrybyron@gmail.com")
                        .password("123456")
                        .build()
        );

        List<Student> students = studentDtos.stream()
                .map(studentDtoRequest -> studentMapper.mapToStudent(studentDtoRequest))
                .toList();

        studentRepository.saveAll(students);

        // When - Action
        // "perform()" Returns a ResultActions Object as Response
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/students")
        );

        // Then - Verify the Output
        // Verify the Status of the Response
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath(
                // "$" for the Whole Json Object
                // $.size() ==> Expected Result / students.size() ==> Actual Result
                "$.size()", CoreMatchers.is(students.size())
            )
        );
    }
}