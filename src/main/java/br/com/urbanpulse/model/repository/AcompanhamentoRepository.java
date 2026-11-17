package br.com.urbanpulse.model.repository;

import br.com.urbanpulse.model.entity.Acompanhamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcompanhamentoRepository extends JpaRepository<Acompanhamento, Long> {
    Acompanhamento findByCodigo(Long codigo);
}
