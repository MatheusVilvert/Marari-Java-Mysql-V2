package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Pedido;
import com.marari.mararimysqlv2.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("pedido/alterar")
    public void editar(@RequestBody Pedido pedido){pedidoService.editar(pedido);}

    @DeleteMapping("pedido/{id}")
    public void excluir(@PathVariable("id")Integer id){
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedidoService.excluir(pedido);
    }

}
