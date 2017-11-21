package com.marari.mararimysqlv2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ItemPedido {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Produto produto;
    private Integer qtd;

    public ItemPedido(Produto produto,Integer qtd) {
        this.produto = produto;
        this.qtd = qtd;
    }

    public ItemPedido() {
    }
}
