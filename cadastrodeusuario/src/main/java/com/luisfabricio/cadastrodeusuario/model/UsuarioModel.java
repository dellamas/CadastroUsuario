package com.luisfabricio.cadastrodeusuario.model;




import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
@Data
@Entity
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String codigo;
    private String nome;
    private LocalDate dataDeNascimento;
    @Lob
    private byte[] foto;
    private Instant dataInclusao;
    private Instant ultimaModificacao;
}