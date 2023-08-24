package br.com.finco.finco_api.common.security;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.finco.finco_api.common.config.EnviromentService;
import br.com.finco.finco_api.user.entities.User;


@Service
public class TokenService {

    @Autowired
    EnviromentService enviromentService;

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(enviromentService.getSecret());

            return JWT.create()
                    .withIssuer("finco-api")
                    .withClaim("email", user.getEmail())
                    .withExpiresAt(getExpirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error create token", e);
        }

    }

    public String validateToken(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(enviromentService.getSecret());

            var tokenData = JWT.require(algorithm)
                    .withIssuer("finco-api")
                    .build()
                    .verify(token.trim())
                    .getClaims();

            return tokenData.get("email").asString();

        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token", e);
        }

    }

    private Instant getExpirationTime() {
        return Instant.now().plusMillis(enviromentService.getExpiration());
    }

}
