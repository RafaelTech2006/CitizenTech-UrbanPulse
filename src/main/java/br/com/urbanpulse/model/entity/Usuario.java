package br.com.urbanpulse.model.entity;

import br.com.urbanpulse.model.dto.UsuarioDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Usuario")
@Table(name = "up_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private String email;
    @Column(length = 20)
    private String telefone;
    private int pontosEngajamento = 0;
    private LocalDateTime data = LocalDateTime.now();

    public Usuario(){
    }

    public Usuario(UsuarioDTO usuarioDTO) {
        this.nome = usuarioDTO.nome();
        this.email = usuarioDTO.email();
        this.telefone = usuarioDTO.telefone();
    }

    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public int getPontosEngajamento() {
        return pontosEngajamento;
    }
    public void setPontosEngajamento(int pontosEngajamento) {
        this.pontosEngajamento = pontosEngajamento;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
