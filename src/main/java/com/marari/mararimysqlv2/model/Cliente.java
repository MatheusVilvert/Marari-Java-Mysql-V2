package com.marari.mararimysqlv2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String email;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Endereco endereco;
    private String cpfCnpj;
    private String rg;
    private String telefone;
    private String info;

    public Cliente(String nome, String email, Endereco endereco, String cpfCnpj, String rg, String telefone, String info) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.cpfCnpj = cpfCnpj;
        this.rg = rg;
        this.telefone = telefone;
        this.info = info;
    }

    public Cliente() {
    }
}
