package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.FormaPagamento;
import com.marari.mararimysqlv2.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @PostMapping("formapagamento/salvar")
    public FormaPagamento cadastrar(@RequestBody FormaPagamento formaPagamento){
        return formaPagamentoService.salvar(formaPagamento);
    }

    @GetMapping("formapagamento/todos")
    public List<FormaPagamento> buscarTodos(){
        return formaPagamentoService.buscarTodos();
    }

    @GetMapping("formapagamento/descricao")
    public List<FormaPagamento> buscarPorDescricao(@RequestParam("descicao")String descricao){
        return formaPagamentoService.buscarDescricao(descricao);
    }

    @DeleteMapping("formapagamento/{id}")
    public void excluir(@PathVariable("id")Integer id){
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setId(id);
        formaPagamentoService.excluir(formaPagamento);
    }

    @PutMapping("formapagamento/alterar")
    public void editar(@RequestBody FormaPagamento formaPagamento){
        formaPagamentoService.editar(formaPagamento);
    }
}
