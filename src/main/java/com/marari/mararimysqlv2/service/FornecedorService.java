package com.marari.mararimysqlv2.service;

import com.marari.mararimysqlv2.model.Endereco;
import com.marari.mararimysqlv2.model.Fornecedor;
import com.marari.mararimysqlv2.repository.EnderecoRepository;
import com.marari.mararimysqlv2.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;
    @Autowired
    EnderecoRepository enderecoRepository;


    public Fornecedor salvar(Fornecedor fornecedor){
        if (fornecedor.getEndereco().getId() == null){
            enderecoRepository.save(fornecedor.getEndereco());
        }else {
            Endereco endereco = enderecoRepository.findOne(fornecedor.getEndereco().getId());
            fornecedor.setEndereco(endereco);
        }
        return fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> buscaPorNome(String nome ){
        if (nome != ""){
            return fornecedorRepository.buscaPorNome(nome);
        }else {
            return fornecedorRepository.findAll();
        }
    }

    public List<Fornecedor> buscarTodos(){return fornecedorRepository.findAll();}

    public List<Fornecedor> listaPorParametro(String parametro){return fornecedorRepository.listaPorParametro('%'+parametro+'%');}

    public void excluir(Fornecedor fornecedor){fornecedorRepository.delete(fornecedor);}

    public void editar(Fornecedor fornecedor){
        Fornecedor fornecedorExistente = fornecedorRepository.findOne(fornecedor.getId());
        if (fornecedorExistente != null){
            fornecedorRepository.save(fornecedor);
        }
    }
}
