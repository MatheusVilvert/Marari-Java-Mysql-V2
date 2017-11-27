package com.marari.mararimysqlv2.model;

import lombok.Data;

@Data
public class objGenerico {

    private Produto produto;
    private Caixa caixa;
    private Pedido pedido;
    private String margemPedido;
    private double valorCusto;
    private String entrada,saida;

}
