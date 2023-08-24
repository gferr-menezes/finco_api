package br.com.finco.finco_api.auth.services;

import br.com.finco.finco_api.common.exceptions.UnauthorizedException;
import br.com.finco.finco_api.common.security.Cryptor;
import br.com.finco.finco_api.common.security.TokenService;
import br.com.finco.finco_api.user.entities.User;
import br.com.finco.finco_api.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    public String verifyUser(String email, String password) {
        User user = userService.getUserByEmail(email);
        
        if(isValid(password, user.getPassword())) {
            return tokenService.generateToken(user);
        }

        throw new UnauthorizedException();
    }

    private boolean isValid(String password, String encryptedPassword) {
        return Cryptor.compare(password, encryptedPassword);
    }
}
