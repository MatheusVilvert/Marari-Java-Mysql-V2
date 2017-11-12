package com.marari.mararimysqlv2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Perfil perfil;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String email, String senha, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }
}
