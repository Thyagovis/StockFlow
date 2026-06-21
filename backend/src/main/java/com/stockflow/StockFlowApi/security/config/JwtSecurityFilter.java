package com.stockflow.StockFlowApi.security.config;

import com.stockflow.StockFlowApi.security.service.JwtService;
import com.stockflow.StockFlowApi.shared.exceptions.InvalidTokenException;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final HandlerExceptionResolver exceptionResolver;

    public JwtSecurityFilter(JwtService jwtService, UsuarioRepository usuarioRepository, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            if (extrairToken(request) instanceof String token) {
                var subject = jwtService.validarToken(token);

                UserDetails user = usuarioRepository.findByLogin(subject)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        }
        catch (InvalidTokenException ex) {

            exceptionResolver.resolveException(
                    request,
                    response,
                    null,
                    ex
            );
        }

    }

    private String extrairToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");

        if (header == null) {
            return null;
        }

        if (!header.startsWith("Bearer ")) {
            throw new InvalidTokenException("Token Invalido");
        }

        return header.substring(7);
    }

}
