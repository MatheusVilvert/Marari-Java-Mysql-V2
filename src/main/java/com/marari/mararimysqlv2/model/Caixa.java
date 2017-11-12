package com.marari.mararimysqlv2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Caixa {
    @Id
    @GeneratedValue
    private Integer id;
    private String data;
    private String descricao;
    private double valor;
    private boolean status;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private FormaPagamento formaPagamento;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private TipoDespesa tipoDespesa;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Cliente cliente;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Fornecedor fornecedor;

    public Caixa(String data, String descricao, double valor, boolean status, FormaPagamento formaPagamento, TipoDespesa tipoDespesa, Cliente cliente, Fornecedor fornecedor) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
        this.formaPagamento = formaPagamento;
        this.tipoDespesa = tipoDespesa;
        this.cliente = cliente;
        this.fornecedor = fornecedor;
    }

    public Caixa() {
    }
}
