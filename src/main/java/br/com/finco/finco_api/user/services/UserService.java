package br.com.finco.finco_api.user.services;

import br.com.finco.finco_api.common.dto.FilterDTO;
import br.com.finco.finco_api.common.dto.PaginationDTO;
import br.com.finco.finco_api.user.dto.UserDTO;
import br.com.finco.finco_api.user.dto.UserOutput;
import br.com.finco.finco_api.user.entities.User;
import br.com.finco.finco_api.user.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserOutput createUser(UserDTO user) {
        User newUser = new User(user.getName(), user.getEmail(), user.getPassword());
        User savedUser = repository.save(newUser);
        return new UserOutput(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UserOutput getUserById(Long id) {
        User user = repository.findById(id).orElseThrow();
        return new UserOutput(user.getId(), user.getName(), user.getEmail());
    }

    public Page<UserOutput> getAllUsers(PaginationDTO paginationDTO, FilterDTO filterDTO) {

        System.out.println(paginationDTO.toString());

        Pageable pageable = PageRequest.of(paginationDTO.getPage(), paginationDTO.getPerPage(), paginationDTO.getSortDirection(), paginationDTO.getSortBy());
        
        Page<User> users = repository.findAllWithPagination(pageable, filterDTO.getSearch());
        return users.map(user -> new UserOutput(user.getId(), user.getName(), user.getEmail()));
    }
}
