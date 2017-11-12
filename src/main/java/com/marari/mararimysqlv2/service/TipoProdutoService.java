package com.marari.mararimysqlv2.service;

import com.marari.mararimysqlv2.model.TipoProduto;
import com.marari.mararimysqlv2.repository.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProdutoService {

    @Autowired
    TipoProdutoRepository tipoProdutoRepository;

    public TipoProduto salvar(TipoProduto tipoProduto){
        return tipoProdutoRepository.save(tipoProduto);
    }

    public List<TipoProduto> buscarTodos(){
        return tipoProdutoRepository.findAll();
    }

    public List<TipoProduto> buscarDescricao(String descricao){
        return tipoProdutoRepository.findByDescricao(descricao);
    }

    public void excluir(TipoProduto tipoProduto){
        tipoProdutoRepository.delete(tipoProduto);
    }

    public void editar(TipoProduto tipoProduto){
        TipoProduto tipoProdutoExistente = tipoProdutoRepository.findOne(tipoProduto.getId());
        if (tipoProdutoExistente != null){
            tipoProdutoRepository.save(tipoProduto);
        }
    }
}
