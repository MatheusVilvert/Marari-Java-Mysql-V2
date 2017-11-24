package com.marari.mararimysqlv2.service;

import com.marari.mararimysqlv2.model.ItemPedido;
import com.marari.mararimysqlv2.model.Pedido;
import com.marari.mararimysqlv2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public Pedido salvar(Pedido pedido){
        double tot = 0;
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        pedido.setData(sdf.format(date));

        pedido.setCliente(clienteRepository.findOne(pedido.getCliente().getId()));
        pedido.setFormaPagamento(formaPagamentoRepository.findOne(pedido.getFormaPagamento().getId()));
        pedido.setUsuario(usuarioRepository.findOne(pedido.getUsuario().getId()));
        pedidoRepository.save(pedido);
        for (int i =0; i<pedido.getItensPedido().size(); i++){
            //pedido.getItensPedido().get(i).setProduto(produtoRepository.findOne(pedido.getItensPedido().get(i).getProduto().getId()));
            itemPedidoRepository.save(pedido.getItensPedido().get(i));
            tot+= pedido.getItensPedido().get(i).getProduto().getPrecoVenda() * pedido.getItensPedido().get(i).getQuantidade();
        }
        ////
        pedido.setValorTotal(tot);
        pedidoRepository.save(pedido);


        return pedido;
    }

    public List<Pedido> buscarTodos(){return pedidoRepository.findAll();}

    public void excluir(Pedido pedido){pedidoRepository.delete(pedido);}

    public void editar(Pedido pedido){
        Pedido pedidoExistente = pedidoRepository.findOne(pedido.getId());
        if (pedidoExistente != null ){
            pedidoRepository.save(pedido);
        }
    }
}
