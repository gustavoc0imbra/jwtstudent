package org.uniara.jwtstudent;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import org.uniara.jwtstudent.controllers.StudentController;
import org.uniara.jwtstudent.security.JwtTokenProvider;
import org.uniara.jwtstudent.services.StudentService;

@WebMvcTest(StudentController.class)
public class GetStudentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    //teste metodo get
    @Test
    public void getStudentsOk() throws Exception {
        Mockito.when(jwtTokenProvider.validateToken(Mockito.anyString())).thenReturn(true);
        Mockito.when(studentService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/students")
                        .header("Authorization", "Bearer test-token"))
                .andExpect(status().isOk());
    }
}


