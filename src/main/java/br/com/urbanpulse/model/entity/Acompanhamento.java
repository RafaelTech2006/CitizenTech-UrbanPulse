package br.com.urbanpulse.model.entity;

import br.com.urbanpulse.model.dto.AcompanhamentoDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Acompanhamento")
@Table(name = "up_acompanhamento")
public class Acompanhamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    // relacionamento de N : 1, muitos acompanhamentos pertencem a uma única ocorrencia
    @ManyToOne
    // cria a coluna de ligação (chave estrangeira),
    // com a regra que não se pode receber valores nulos
    @JoinColumn(name = "id_ocorrencia", nullable = false)
    private Ocorrencia ocorrencia;
    @Column(length = 100)
    private String etapa;
    @Column(length = 100)
    private String responsavel;
    private LocalDateTime data = LocalDateTime.now();

    public Acompanhamento() {
    }

    public Acompanhamento(AcompanhamentoDTO acompanhamentoDTO, Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
        this.etapa = acompanhamentoDTO.etapa();
        this.responsavel = acompanhamentoDTO.responsavel();
    }

    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }
    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }
    public String getEtapa() {
        return etapa;
    }
    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }
    public String getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
