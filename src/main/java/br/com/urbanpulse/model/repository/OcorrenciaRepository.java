package br.com.urbanpulse.model.repository;

import br.com.urbanpulse.model.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    Ocorrencia findByCodigo(Long codigo);
}
