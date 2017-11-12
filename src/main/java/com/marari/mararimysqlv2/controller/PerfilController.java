package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Perfil;
import com.marari.mararimysqlv2.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PerfilController {

    @Autowired
    PerfilService perfilService;

    @GetMapping("/perfil/todos")
    public List<Perfil> buscarTodos(){return perfilService.buscarTodos();}

    @PostMapping("/perfil/salvar")
    public Perfil salvar(@RequestBody Perfil perfil){return perfilService.salvar(perfil);}

    @DeleteMapping("/perfil/{id}")
    public void excluir(@PathVariable("id")Integer id){
        Perfil perfil = new Perfil();
        perfil.setId(id);
        perfilService.excluir(perfil);
    }

    @PutMapping("/perfil/alterar")
    public void alterar(@RequestBody Perfil perfil){
        perfilService.editar(perfil);
    }
}
