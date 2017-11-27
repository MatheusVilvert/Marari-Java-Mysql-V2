package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Produto;
import com.marari.mararimysqlv2.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("produto/salvar")
    public Produto cadastrar(@RequestBody Produto produto){
        return produtoService.salvar(produto);
    }

    @PutMapping("produto/alterar")
    public void editar(@RequestBody Produto produto){
        produtoService.editar(produto);
    }

    @GetMapping("produto/todos")
    public List<Produto> buscarTodos(){return produtoService.buscarTodos();}

    @GetMapping("produto/precos")
    public List<Produto> listaDePrecos(@Param("parametro")String parametro){return produtoService.listaDePrecos(parametro);}

    @GetMapping("produto/estoque")
    public List<Produto> posicaoEstoque(@Param("parametro")String parametro){return produtoService.posicaoEstoque(parametro);}

    @GetMapping("produto/listaprodutos")
    public List<Produto> listaProduto(@Param("nome")String nome){return produtoService.listaProduto(nome);}

    @DeleteMapping("produto/{id}")
    public void excluir(@PathVariable("id")Integer id){
        Produto produto = new Produto();
        produto.setId(id);
        produtoService.excluir(produto);
    }
}
