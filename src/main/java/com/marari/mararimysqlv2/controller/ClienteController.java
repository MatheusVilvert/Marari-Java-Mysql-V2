package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Cliente;
import com.marari.mararimysqlv2.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("cliente/salvar")
    public Cliente cadastrar(@RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @PutMapping("cliente/alterar")
    public void editar(@RequestBody Cliente cliente){
        clienteService.editar(cliente);
    }

    @GetMapping("cliente/todos")
    public List<Cliente> buscarTodos(){return clienteService.buscarTodos();}

    @GetMapping("cliente/parametro")
    public List<Cliente> buscarPorParametro(@Param("parametro") String parametro){return clienteService.buscarPorParametro(parametro);}

    @GetMapping("cliente/nome")
    public List<Cliente> buscarPorNome(@Param("nome")String nome){return clienteService.buscarPorNome(nome);}

    @DeleteMapping("cliente/{id}")
    public void excluir(@PathVariable("id")Integer id){
        Cliente cliente = new Cliente();
        cliente.setId(id);
        clienteService.excluir(cliente);
    }
}
