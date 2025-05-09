package org.uniara.jwtstudent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.uniara.jwtstudent.constant.Constant;
import org.uniara.jwtstudent.models.ResponseToken;
import org.uniara.jwtstudent.models.User;
import org.uniara.jwtstudent.security.JwtTokenProvider;
import org.uniara.jwtstudent.services.UserService;

import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping(Constant.API_AUTH)
    public ResponseToken getToken(@RequestBody User user) {
        Optional<User> userFound = userService.findByEmail(user.getEmail());

        if (userFound.isEmpty() || (!userFound.get().getEmail().equals(user.getEmail()) && !userFound.get().getPassword().equals(user.getPassword()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado ou senha incorreta!");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());
        return new ResponseToken("Autenticado!", token);
    }
}
