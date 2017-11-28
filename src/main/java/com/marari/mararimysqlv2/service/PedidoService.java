package com.marari.mararimysqlv2.service;

import com.marari.mararimysqlv2.model.ItemPedido;
import com.marari.mararimysqlv2.model.Pedido;
import com.marari.mararimysqlv2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
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
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        pedido.setData(sdf.format(date));


        pedido.setCliente(clienteRepository.findOne(pedido.getCliente().getId()));
        pedido.setFormaPagamento(formaPagamentoRepository.findOne(pedido.getFormaPagamento().getId()));
        pedido.setUsuario(usuarioRepository.findOne(pedido.getUsuario().getId()));
        pedidoRepository.save(pedido);
        for (int i =0; i<pedido.getItensPedido().size(); i++){
            pedido.getItensPedido().get(i).setProduto(produtoRepository.findOne(pedido.getItensPedido().get(i).getProduto().getId()));
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

    public double vendasHoje(){
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return pedidoRepository.vendasHoje(sdf.format(date));
    }

    public double vendasPeriodo(String dataIni, String dataFin){
        return pedidoRepository.vendasPeriodo(dataIni,dataFin);
    }

    public List<String> vendasDiaPeriodo(String dataIni, String dataFin){
        return pedidoRepository.vendasDiaPeriodo(dataIni,dataFin);
    }

    public List<Double> vendasValorPeriodo(String dataIni, String dataFin){
        return pedidoRepository.vendasValorPeriodo(dataIni,dataFin);
    }

    public List<Pedido> buscaParametro(String dataIni, String dataFin,String nomeCliente,String nomeVendedor){
        return pedidoRepository.buscaParametro(dataIni,dataFin,nomeVendedor,nomeCliente);
    }

    public List<Pedido> buscaPorCliente(String dataIni, String dataFin,String nomeCliente){
        return pedidoRepository.buscaPorCliente(dataIni,dataFin,nomeCliente);
    }

    public List<Pedido> buscaPorVendedor(String dataIni, String dataFin,String nomeVendedor){
        return pedidoRepository.buscaPorVendedor(dataIni,dataFin,nomeVendedor);
    }

    public List<Pedido> teste(){return pedidoRepository.teste();}

    public List<Pedido> listaPedido(String dataIni, String dataFin,String nomeCliente,String nomeVendedor){
        if (dataIni != "" && dataFin != "" && nomeCliente != "" && nomeVendedor != "" ){
            nomeCliente = '%'+nomeCliente+'%';
            nomeVendedor = '%'+nomeVendedor+'%';
           return pedidoRepository.buscaParametro(dataIni,dataFin,nomeVendedor,nomeCliente);
        }else if (dataIni != "" && dataFin != "" && nomeCliente.equals("") && nomeVendedor.equals("")){
            return pedidoRepository.buscaPorData(dataIni,dataFin);
        }else if (dataIni != "" && dataFin != "" && nomeCliente!= ""){
            nomeCliente = '%'+nomeCliente+'%';
            return pedidoRepository.buscaPorCliente(dataIni,dataFin,nomeCliente);
        }else if (dataIni != "" && dataFin != "" && nomeVendedor!= ""){
            nomeVendedor = '%'+nomeVendedor+'%';
            return pedidoRepository.buscaPorVendedor(dataIni,dataFin,nomeVendedor);
        }else {
            return null;
        }
    }

    public List<Pedido> buscaPorData(String dataIni, String dataFin){return pedidoRepository.buscaPorData(dataIni,dataFin);}

    public void editar(Pedido pedido){
        Pedido pedidoExistente = pedidoRepository.findOne(pedido.getId());
        if (pedidoExistente != null ){
            pedidoRepository.save(pedido);
        }
    }
}
