package com.marari.mararimysqlv2.service;

import com.marari.mararimysqlv2.model.*;
import com.marari.mararimysqlv2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    FornecedorRepository fornecedorRepository;
    @Autowired
    TipoProdutoRepository tipoProdutoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EnderecoRepository enderecoRepository;

    public Produto salvar(Produto produto){
        if (produto.getFornecedor().getEndereco().getId() == null){
            enderecoRepository.save(produto.getFornecedor().getEndereco());
        }else {
            Endereco endereco = enderecoRepository.findOne(produto.getFornecedor().getEndereco().getId());
            produto.getFornecedor().setEndereco(endereco);
        }
        //setando fornecedor
        if (produto.getFornecedor().getId() == null){
            fornecedorRepository.save(produto.getFornecedor());
        }else {
            Fornecedor fornecedor = fornecedorRepository.findOne(produto.getFornecedor().getId());
            produto.setFornecedor(fornecedor);
        }
        //setando tipo do produto
        if (produto.getTipoProduto().getId() == null){
            tipoProdutoRepository.save(produto.getTipoProduto());
        }else {
            TipoProduto tipoProduto = tipoProdutoRepository.findOne(produto.getTipoProduto().getId());
            produto.setTipoProduto(tipoProduto);
        }

        //setando usuario
        if (produto.getUsuario().getId() == null){
            usuarioRepository.save(produto.getUsuario());
        }else {
            Usuario usuario = usuarioRepository.findOne(produto.getUsuario().getId());
            produto.setUsuario(usuario);
        }
        return produtoRepository.save(produto);

    }

    public List<Produto> buscarTodos(){return produtoRepository.findAll();}

    public List<Produto> listaDePrecos(String parametro){return produtoRepository.listaDePrecos('%'+parametro+'%');}

    public List<Produto> posicaoEstoque(String parametro){return produtoRepository.posicaoEstoque('%'+parametro+'%');}

    public void excluir(Produto produto){produtoRepository.delete(produto);}

    public void editar(Produto produto){
        Produto produtoExistente = produtoRepository.findOne(produto.getId());
        if (produtoExistente != null){
            produtoRepository.save(produto);
        }
    }
}
