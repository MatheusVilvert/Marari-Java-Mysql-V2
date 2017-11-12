package com.marari.mararimysqlv2.service;

import com.marari.mararimysqlv2.model.Cliente;
import com.marari.mararimysqlv2.model.Endereco;
import com.marari.mararimysqlv2.repository.ClienteRepository;
import com.marari.mararimysqlv2.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente salvar(Cliente cliente){
        if (cliente.getEndereco().getId() == null){
            enderecoRepository.save(cliente.getEndereco());
        }else {
            Endereco endereco = enderecoRepository.findOne(cliente.getEndereco().getId());
            cliente.setEndereco(endereco);
        }
        return clienteRepository.save(cliente);

    }

    public List<Cliente> buscarTodos(){return clienteRepository.findAll();}

    public List<Cliente> buscarPorParametro(String parametro){return clienteRepository.buscarPorParametro('%'+parametro+'%');}

    public void excluir(Cliente cliente){clienteRepository.delete(cliente);}

    public void editar(Cliente cliente){
        Cliente clienteExistente = clienteRepository.findOne(cliente.getId());
        if (clienteExistente != null){
            clienteRepository.save(cliente);
        }
    }

}
