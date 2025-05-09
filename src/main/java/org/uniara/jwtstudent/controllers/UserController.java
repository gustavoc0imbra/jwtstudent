package org.uniara.jwtstudent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniara.jwtstudent.constant.Constant;
import org.uniara.jwtstudent.models.User;
import org.uniara.jwtstudent.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(Constant.API_USERS)
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(Constant.API_USERS + "/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping(Constant.API_USERS)
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping(Constant.API_USERS)
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping(Constant.API_USERS + "/{id}")
    public ResponseEntity<User> deleteById(@PathVariable("id") String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
