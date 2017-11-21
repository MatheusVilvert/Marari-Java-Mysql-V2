package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Vendedor;
import com.marari.mararimysqlv2.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @PostMapping("vendedor/salvar")
    public Vendedor cadastrar(@RequestBody Vendedor vendedor){return vendedorService.salvar(vendedor);}

    @GetMapping("vendedor/todos")
    public List<Vendedor> buscarTodos(){return vendedorService.buscarTodos();}

    @DeleteMapping("vendedor/{id}")
    public void excluir(@PathVariable("id")Integer id){
        Vendedor vendedor = vendedorService.buscarPorId(id);
        vendedorService.excluir(vendedor);

    }
}
