package br.com.finco.finco_api.user.services;

import br.com.finco.finco_api.common.dto.FilterDTO;
import br.com.finco.finco_api.common.dto.PaginationDTO;
import br.com.finco.finco_api.user.dto.ProfileOuput;
import br.com.finco.finco_api.user.dto.UserInputDTO;
import br.com.finco.finco_api.user.dto.UserOutput;
import br.com.finco.finco_api.user.entities.Profile;
import br.com.finco.finco_api.user.entities.User;
import br.com.finco.finco_api.user.repositories.ProfileRepository;
import br.com.finco.finco_api.user.repositories.UserRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    public UserOutput createUser(UserInputDTO user) {
        User newUser = new User(user.getEmail(), user.getPassword());
        User savedUser = repository.save(newUser);

        Profile profile = new Profile();
        profile.setName(user.getProfile().getName());
        profile.setUser(savedUser);
        profileRepository.save(profile);

        ProfileOuput profileOuput = new ProfileOuput(profile.getId(), profile.getName(), profile.getAvatarUrl());

        return new UserOutput(savedUser.getId(), savedUser.getEmail(), profileOuput);
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UserOutput getUserById(Long id) {
        User user = repository.findById(id).orElseThrow();
        return new UserOutput(user.getId(), user.getEmail(), new ProfileOuput(user.getProfile().getId(), user.getProfile().getName(), user.getProfile().getAvatarUrl()));
    }

    public Page<UserOutput> getAllUsers(PaginationDTO paginationDTO, FilterDTO filterDTO) {
        Pageable pageable = PageRequest.of(paginationDTO.getPage(), paginationDTO.getPerPage(), paginationDTO.getSortDirection(), paginationDTO.getSortBy());
        
        Page<User> users = repository.findAllWithPagination(pageable, filterDTO.getSearch());
        return users.map(user -> new UserOutput(user.getId(), user.getEmail(), new ProfileOuput(user.getProfile().getId(), user.getProfile().getName(), user.getProfile().getAvatarUrl())));
    }
}
