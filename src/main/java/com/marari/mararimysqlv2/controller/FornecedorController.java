package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Fornecedor;
import com.marari.mararimysqlv2.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping("fornecedor/salvar")
    public Fornecedor cadastrar(@RequestBody Fornecedor fornecedor){
        return fornecedorService.salvar(fornecedor);
    }

    @PutMapping("fornecedor/alterar")
    public void editar(@RequestBody Fornecedor fornecedor){
        fornecedorService.editar(fornecedor);
    }

    @GetMapping("fornecedor/todos")
    public List<Fornecedor> buscarTodos(){return fornecedorService.buscarTodos();}

    @GetMapping("fornecedor/parametro")
    public List<Fornecedor> listaPorParametro(@Param("parametro")String parametro){return fornecedorService.listaPorParametro(parametro);}

    @DeleteMapping("fornecedor/{id}")
    public void excluir(@PathVariable("id")Integer id){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);
        fornecedorService.excluir(fornecedor);
    }
}
