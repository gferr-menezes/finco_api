package br.com.finco.finco_api.user.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.finco.finco_api.user.dto.ProfileOuputDTO;
import br.com.finco.finco_api.user.dto.UserOutputDTO;
import br.com.finco.finco_api.user.entities.User;

@Component
public class UserMapper {

    public UserOutputDTO toDTO(User user) {
        return new UserOutputDTO(user.getId(), user.getEmail(),
                new ProfileOuputDTO(user.getProfile().getId(), user.getProfile().getName(),
                        user.getProfile().getAvatarUrl()));
    }

}
