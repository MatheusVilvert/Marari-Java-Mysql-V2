package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Cliente;
import com.marari.mararimysqlv2.model.Pedido;
import com.marari.mararimysqlv2.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import sun.plugin.javascript.navig.LinkArray;

import java.lang.reflect.Array;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("pedido/salvar")
    public Pedido cadastrar(@RequestBody Pedido pedido){return pedidoService.salvar(pedido);}

    @GetMapping("pedido/todos")
    public List<Pedido> buscarTodos(){return pedidoService.buscarTodos();}

    @GetMapping("pedido/vendashoje")
    public double vendasHoje(){return pedidoService.vendasHoje();}

    @GetMapping("pedido/vendasdiaperiodo")
    public List<String> vendasDiaPeriodo(@Param("dataIni")String dataIni, @Param("dataFin")String dataFin){
        return pedidoService.vendasDiaPeriodo(dataIni,dataFin);
    }

    @GetMapping("pedido/vendasvalorperiodo")
    public List<Double> vendasValorPeriodo(@Param("dataIni")String dataIni, @Param("dataFin")String dataFin){
        return pedidoService.vendasValorPeriodo(dataIni,dataFin);
    }

    @GetMapping("pedido/teste")
    public List<Pedido> teste(){
        return pedidoService.teste();
    }

    @GetMapping("pedido/lista")
    public List<Pedido> listaPedido(@Param("dataIni")String dataIni, @Param("dataFin") String dataFin, @Param("nomeCliente")String nomeCliente,@Param("nomeVendedor")String nomeVendedor){
        return pedidoService.listaPedido(dataIni,dataFin,nomeCliente,nomeVendedor);
    }

    @GetMapping("pedido/vendasperiodo")
    public double vendasPeriodo(@Param("dataIni")String dataIni,@Param("dataFin")String dataFin){
        return pedidoService.vendasPeriodo(dataIni,dataFin);
    }

    @PutMapping("pedido/alterar")
    public void editar(@RequestBody Pedido pedido){pedidoService.editar(pedido);}

    @DeleteMapping("pedido/{id}")
    public void excluir(@PathVariable("id")Integer id){
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedidoService.excluir(pedido);
    }

}
