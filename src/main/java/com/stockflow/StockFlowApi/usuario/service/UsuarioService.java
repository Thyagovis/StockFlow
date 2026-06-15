package com.stockflow.StockFlowApi.usuario.service;

import com.stockflow.StockFlowApi.shared.exceptions.ConflictException;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioMapper;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioRegistroDTO;
import com.stockflow.StockFlowApi.usuario.dto.UsuarioResponseDTO;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioRepository;
import com.stockflow.StockFlowApi.usuario.repository.UsuarioSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioResponseDTO>listAll(Boolean ativo) {
        var spec = Specification.where(UsuarioSpec.comAtivo(ativo));

        return usuarioRepository.findAll(spec).stream()
                .map(UsuarioMapper::paraResponseDTO)
                .toList();
    }

    @Transactional
    public UsuarioResponseDTO saveUsuario(UsuarioRegistroDTO registroDTO) {
        var senhaCodificada = passwordEncoder.encode(registroDTO.senha());

        if (usuarioRepository.existsByEmailOrLogin(registroDTO.email(), registroDTO.login())) {
            throw new ConflictException("Usuario existente");
        }

        Usuario usuario = new Usuario(
                registroDTO.nome().toLowerCase(),
                registroDTO.email().trim().toLowerCase(),
                registroDTO.login().trim(),
                senhaCodificada,
                registroDTO.cargo()
        );

        return UsuarioMapper.paraResponseDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

}
