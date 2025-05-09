package org.uniara.jwtstudent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uniara.jwtstudent.constant.Constant;
import org.uniara.jwtstudent.models.User;
import org.uniara.jwtstudent.security.JwtTokenProvider;
import org.uniara.jwtstudent.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final int BEARER_OFFSET = 7;

    @GetMapping(Constant.API_USERS)
    public ResponseEntity<List<User>> findAll(@RequestHeader("Authorization") String token) {
        if(!jwtTokenProvider.validateToken(token.substring(BEARER_OFFSET))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado!");
        }

        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(Constant.API_USERS + "/{id}")
    public ResponseEntity<Optional<User>> findById(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if(!jwtTokenProvider.validateToken(token.substring(BEARER_OFFSET))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado!");
        }

        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping(Constant.API_USERS)
    public ResponseEntity<User> save(@RequestHeader("Authorization") String token, @RequestBody User user) {
        if(!jwtTokenProvider.validateToken(token.substring(BEARER_OFFSET))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping(Constant.API_USERS)
    public ResponseEntity<User> update(@RequestHeader("Authorization") String token, @RequestBody User user) {
        if(!jwtTokenProvider.validateToken(token.substring(BEARER_OFFSET))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado!");
        }

        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping(Constant.API_USERS + "/{id}")
    public ResponseEntity<User> deleteById(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        if(!jwtTokenProvider.validateToken(token.substring(BEARER_OFFSET))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado!");
        }

        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
