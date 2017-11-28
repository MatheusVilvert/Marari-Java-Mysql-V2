package com.marari.mararimysqlv2.model;

import lombok.Data;

@Data
public class objGenerico {

    private Produto produto;
    private Caixa caixa;
    private Pedido pedido;
    private String margemPedido;
    private String margemProduto;
    private double valorCusto;
    private String entrada,saida;
    private String valorTotal;


}
