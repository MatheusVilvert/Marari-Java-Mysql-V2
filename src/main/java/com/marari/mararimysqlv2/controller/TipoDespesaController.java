package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.TipoDespesa;
import com.marari.mararimysqlv2.service.TipoDespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TipoDespesaController {

    @Autowired
    private TipoDespesaService tipoDespesaService;

    @PostMapping("tipodespesa/salvar")
    public TipoDespesa cadastrar (@RequestBody TipoDespesa tipoDespesa){
        return tipoDespesaService.salvar(tipoDespesa);
    }

    @GetMapping("tipodespesa/todos")
    public List<TipoDespesa> buscarTodos(){
        return tipoDespesaService.buscarTodos();
    }

    @GetMapping("tipodespesa/descricao")
    public List<TipoDespesa> buscarPorDescricao(@RequestParam("descricao")String descricao){
        return tipoDespesaService.buscarPorDescricao(descricao);
    }

    @DeleteMapping("tipodespesa/{id}")
    public void excluir(@PathVariable("id") Integer id){
        TipoDespesa tipoDespesa = new TipoDespesa();
        tipoDespesa.setId(id);
        tipoDespesaService.excluir(tipoDespesa);
    }

    @PutMapping("tipodespesa/alterar")
    public void editar(@RequestBody TipoDespesa tipoDespesa ){
        tipoDespesaService.editar(tipoDespesa);
    }
}
