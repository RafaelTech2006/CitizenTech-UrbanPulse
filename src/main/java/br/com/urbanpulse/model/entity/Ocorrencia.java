package br.com.urbanpulse.model.entity;

import br.com.urbanpulse.model.dto.OcorrenciaDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Ocorrencia")
@Table(name = "up_ocorrencia")
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(length = 100)
    private String titulo;

    @Column(length = 50)
    private String categoria;

    @Column(length = 500)
    private String descricao;

    @Column(length = 200)
    private String endereco;

    private String fotoAntesUrl;
    private Integer apoios = 0;
    private String status = "ABERTA";
    // relacionamento de N : 1, muitas ocorrencias podem ser cadastradas por um único usuário
    @ManyToOne
    // cria a coluna de ligação (chave estrangeira),
    // com a regra que não se pode receber valores nulos
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    // relacionamento 1 : N, uma única ocorrência está associada a uma lista de muitos acompanhamentos
    // indica que a chave estrangeira é o atributo ocorrencia da classe Acompanhamento
    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL) // espalha todas as operações de persistência do pai para os filhos
    private List<Acompanhamento> acompanhamentos;

    private LocalDateTime data = LocalDateTime.now();

    public Ocorrencia(){
    }

    public Ocorrencia(OcorrenciaDTO ocorrenciaDTO, Usuario usuario) {
        this.titulo = ocorrenciaDTO.titulo();
        this.categoria = ocorrenciaDTO.categoria();
        this.descricao = ocorrenciaDTO.descricao();
        this.endereco = ocorrenciaDTO.endereco();
        this.fotoAntesUrl = ocorrenciaDTO.fotoAntesUrl();
        this.usuario = usuario;
    }

    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getFotoAntesUrl() {
        return fotoAntesUrl;
    }
    public void setFotoAntesUrl(String fotoAntesUrl) {
        this.fotoAntesUrl = fotoAntesUrl;
    }
    public Integer getApoios() {
        return apoios;
    }
    public void setApoios(Integer apoios) {
        this.apoios = apoios;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<Acompanhamento> getAcompanhamentos() {
        return acompanhamentos;
    }
    public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
        this.acompanhamentos = acompanhamentos;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
