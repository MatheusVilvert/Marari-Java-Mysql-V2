package com.marari.mararimysqlv2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Fornecedor {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Endereco endereco;
    private String cpfCnpj;
    private String rgIe;
    private String telefone;
    private String contato;
    private String info;
    private String email;

    public Fornecedor(String nome, Endereco endereco, String cpfCnpj, String rgIe, String telefone, String contato, String info, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpfCnpj = cpfCnpj;
        this.rgIe = rgIe;
        this.telefone = telefone;
        this.contato = contato;
        this.info = info;
        this.email = email;
    }

    public Fornecedor() {
    }
}
