package br.com.urbanpulse.model.repository;

import br.com.urbanpulse.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCodigo(Long codigo);
}
