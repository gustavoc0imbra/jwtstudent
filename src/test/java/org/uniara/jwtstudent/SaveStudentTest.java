package org.uniara.jwtstudent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.uniara.jwtstudent.controllers.StudentController;
import org.uniara.jwtstudent.models.Student;
import org.uniara.jwtstudent.security.JwtTokenProvider;
import org.uniara.jwtstudent.services.StudentService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class SaveStudentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Deve salvar estudante com autenticação válida e retornar 201")
    public void shouldSaveStudentWhenAuthenticated() throws Exception {
        Student student = new Student();
        student.setName("Novo Aluno");
        student.setEmail("novo.aluno@email.com");
        student.setGrade1(7);
        student.setGrade2(8);
        student.setAverage(7.5f);

        when(jwtTokenProvider.validateToken(Mockito.anyString())).thenReturn(true);
        when(studentService.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/students")
                        .header("Authorization", "Bearer token-valido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(student)))
                .andExpect(status().isCreated());
    }
}