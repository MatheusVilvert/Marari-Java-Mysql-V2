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
    private Integer quantidade;

    public ItemPedido(Produto produto,Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedido() {
    }
}
