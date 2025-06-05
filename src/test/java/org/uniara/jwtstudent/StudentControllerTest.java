package org.uniara.jwtstudent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.uniara.jwtstudent.controllers.StudentController;
import org.uniara.jwtstudent.models.Student;
import org.uniara.jwtstudent.security.JwtTokenProvider;
import org.uniara.jwtstudent.services.StudentService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private JwtTokenProvider tokenProvider;

    @Test
    @DisplayName("O usuário não deve conseguir gravar sem estar autenticado")
    public void it_should_not_be_able_to_save_without_authentication() throws Exception {
        Student student = new Student();
        student.setName("Teste");
        student.setEmail("teste@email.com");
        student.setGrade1(10);
        student.setGrade2(10);

        when(studentService.save(student)).thenReturn(student);
        when(tokenProvider.generateToken("")).thenReturn("token");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/students")
                        .header("Authorization", "Bearer sem token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(student)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("O usuário não deve conseguir atualizar sem estar autenticado")
    public void it_should_not_be_able_to_update_without_authentication() throws Exception {
        Student student = new Student();
        student.setId(Mockito.anyString());
        student.setName("Teste");
        student.setEmail("teste@email.com");
        student.setGrade1(10);
        student.setGrade2(10);

        when(studentService.save(student)).thenReturn(student);
        when(tokenProvider.generateToken("")).thenReturn("token");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/students")
                        .header("Authorization", "Bearer sem token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(student)))
                .andExpect(status().isUnauthorized());
    }
}
