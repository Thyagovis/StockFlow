package com.stockflow.StockFlowApi.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private static final String SECRET = "MeuSecret";

    public String gerarToken(Usuario usuario) {
        return JWT.create()
                .withSubject(usuario.getLogin())
                .withIssuedAt(Instant.now())
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String validarToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getSubject();
    }

}
