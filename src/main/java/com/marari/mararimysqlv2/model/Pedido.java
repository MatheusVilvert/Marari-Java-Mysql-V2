package com.marari.mararimysqlv2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ItemPedido> itemPedidoList;
    private Double valorTotal;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Usuario usuario;
    private String info;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cliente cliente;
    private String data;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private FormaPagamento formaPagamento;

    public Pedido(List<ItemPedido> itemPedidoList, Double valorTotal, Usuario usuario, String info, Cliente cliente, String data, FormaPagamento formaPagamento) {
        this.itemPedidoList = itemPedidoList;
        this.valorTotal = valorTotal;
        this.usuario = usuario;
        this.info = info;
        this.cliente = cliente;
        this.data = data;
        this.formaPagamento = formaPagamento;
    }

    public Pedido() {
    }
}
