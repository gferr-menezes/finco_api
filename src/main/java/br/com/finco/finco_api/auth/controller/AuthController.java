package br.com.finco.finco_api.auth.controller;

import br.com.finco.finco_api.auth.dto.InputLoginDTO;
import br.com.finco.finco_api.auth.dto.OutputLoginDTO;
import br.com.finco.finco_api.auth.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public OutputLoginDTO login(@RequestBody @Valid InputLoginDTO loginDTO) {

        var token = authService.verifyUser(loginDTO.getEmail(), loginDTO.getPassword());

        return new OutputLoginDTO(
                "Bearer",
                token
        );
    }
}
