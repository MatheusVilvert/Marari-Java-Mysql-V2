package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Cliente;
import com.marari.mararimysqlv2.service.ClienteService;
import com.marari.mararimysqlv2.service.EnderecoService;
import com.marari.mararimysqlv2.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
public class RelatorioController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
   private ProdutoService produtoService;

    @GetMapping("teste")
    public ModelAndView teste(){
        ModelAndView mv = new ModelAndView("teste");

//
        mv.addObject("testes", clienteService.buscarTodos());
        mv.addObject("produtos",produtoService.buscarTodos());
        return mv;
    }

    @GetMapping("teste2")
    public List<?> teste2(){
        return clienteService.teste();
    }
}
