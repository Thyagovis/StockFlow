package com.stockflow.StockFlowApi.usuario.repository;
import com.stockflow.StockFlowApi.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
