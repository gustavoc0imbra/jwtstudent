package org.uniara.jwtstudent.controllers;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uniara.jwtstudent.constant.Constant;
import org.uniara.jwtstudent.models.Student;
import org.uniara.jwtstudent.security.JwtTokenProvider;
import org.uniara.jwtstudent.services.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    private final int BEARER_OFFSET = 7;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping(Constant.API_STUDENTS)
    public ResponseEntity<List<Student>> findAll(@RequestHeader("Authorization") String token) {
        if (!jwtTokenProvider.validateToken(token.substring(BEARER_OFFSET))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado!");
        }

        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping(Constant.API_STUDENTS + "/{id}")
    public ResponseEntity<Optional<Student>> findById(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if (!jwtTokenProvider.validateToken(token.substring(BEARER_OFFSET))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado!");
        }

        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping(Constant.API_STUDENTS)
    public ResponseEntity<Student> save(@RequestHeader("Authorization") String token, @RequestBody Student student) {
        if (!jwtTokenProvider.validateToken(token.substring(BEARER_OFFSET))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student));
    }

    @PutMapping(Constant.API_STUDENTS)
    public ResponseEntity<Student> update(@RequestHeader("Authorization") String token, @RequestBody Student student) {
        if (!jwtTokenProvider.validateToken(token.substring(BEARER_OFFSET))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado!");
        }

        return ResponseEntity.ok(studentService.save(student));
    }

    @DeleteMapping(Constant.API_STUDENTS + "/{id}")
    public ResponseEntity<Student> delete(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if (!jwtTokenProvider.validateToken(token.substring(BEARER_OFFSET))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado!");
        }

        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
