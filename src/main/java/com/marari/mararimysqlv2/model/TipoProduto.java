package com.marari.mararimysqlv2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TipoProduto {
    @Id
    @GeneratedValue
    private Integer id;
    private String descricao;

    public TipoProduto(String descricao) {
        this.descricao = descricao;
    }

    public TipoProduto() {
    }
}
