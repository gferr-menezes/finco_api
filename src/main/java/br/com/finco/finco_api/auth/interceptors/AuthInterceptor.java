package br.com.finco.finco_api.auth.interceptors;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.finco.finco_api.common.exceptions.UnauthorizedException;
import br.com.finco.finco_api.common.security.TokenService;


@Service
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException();
        } else {
            String bearerToken = token.replace("Bearer", "");
            tokenService.validateToken(bearerToken);
        }

        return true;
    }

}
