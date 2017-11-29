package com.marari.mararimysqlv2.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue
    private Integer id;
    private String descricao;
    private String codBarras;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Fornecedor fornecedor;
    private Double precoCusto;
    private Double precoVenda;
    private Double precoMinVenda;
    private Double precoMaxVenda;
    private Double comissaoVenda;
    private Integer qtdEstoque;
    private Integer qtdMinEstoque;
    private Double altura;
    private Double peso;
    private Double largura;
    private Double profundidade;
    private String medidaProduto;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private TipoProduto tipoProduto;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Usuario usuario;
    private String validade;

    public Produto(String descricao, String codBarras, Fornecedor fornecedor, Double precoCusto, Double precoVenda, Double precoMinVenda, Double precoMaxVenda, Double comissaoVenda, Integer qtdEstoque, Integer qtdMinEstoque, Double altura, Double peso, Double largura, Double profundidade, String medidaProduto, TipoProduto tipoProduto, Usuario usuario, String validade) {
        this.descricao = descricao;
        this.codBarras = codBarras;
        this.fornecedor = fornecedor;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.precoMinVenda = precoMinVenda;
        this.precoMaxVenda = precoMaxVenda;
        this.comissaoVenda = comissaoVenda;
        this.qtdEstoque = qtdEstoque;
        this.qtdMinEstoque = qtdMinEstoque;
        this.altura = altura;
        this.peso = peso;
        this.largura = largura;
        this.profundidade = profundidade;
        this.medidaProduto = medidaProduto;
        this.tipoProduto = tipoProduto;
        this.usuario = usuario;
        this.validade = validade;
    }

    public Produto() {
    }
}