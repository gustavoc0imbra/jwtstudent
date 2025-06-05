package org.uniara.jwtstudent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.uniara.jwtstudent.controllers.StudentController;
import org.uniara.jwtstudent.security.JwtTokenProvider;
import org.uniara.jwtstudent.services.StudentService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class DeleteStudentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("Deve deletar estudante por ID com autenticação válida e retornar 204")
    public void shouldDeleteStudentWithValidToken() throws Exception {
        String studentId = "123";

        Mockito.when(jwtTokenProvider.validateToken(Mockito.anyString())).thenReturn(true);
        Mockito.doNothing().when(studentService).deleteById(studentId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/students/{id}", studentId)
                        .header("Authorization", "Bearer token-valido"))
                .andExpect(status().isNoContent());
    }
}
