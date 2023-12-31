package br.com.finco.finco_api.user.controller;

import br.com.finco.finco_api.common.dto.FilterDTO;
import br.com.finco.finco_api.common.dto.PaginationDTO;
import br.com.finco.finco_api.common.dto.PaginationResponseDTO;
import br.com.finco.finco_api.user.dto.UserInputDTO;
import br.com.finco.finco_api.user.dto.UserOutputDTO;
import br.com.finco.finco_api.user.services.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public UserOutputDTO create(@RequestBody @Valid UserInputDTO user) {
        return service.createUser(user);
    }

    @GetMapping
    public PaginationResponseDTO<UserOutputDTO> getAllUsers(PaginationDTO paginationDTO, FilterDTO filterDTO) {
        return service.getAllUsers(paginationDTO, filterDTO);
    }

}
