package com.stockflow.StockFlowApi.security.controller;

import com.stockflow.StockFlowApi.usuario.entity.*;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody @Valid UsuarioRegistroDTO registerDTO) {

        var senhaCodificada = passwordEncoder.encode(registerDTO.senha());

        Usuario usuario = new Usuario(
                registerDTO.nome(),
                registerDTO.email(),
                registerDTO.login(),
                senhaCodificada,
                registerDTO.cargo()
        );

        usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.paraResponseDTO(usuario));
    }
}
