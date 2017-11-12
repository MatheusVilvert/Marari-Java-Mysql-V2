package com.marari.mararimysqlv2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
public class Perfil {
    @Id
    @SequenceGenerator(name = "seq_perfil", sequenceName = "seq_perfil")
    @GeneratedValue(generator = "seq_perfil")
    private Integer id;
    private String descricao;

    public Perfil() {
    }

    public Perfil(String descricao) {
        this.descricao = descricao;
    }
}
