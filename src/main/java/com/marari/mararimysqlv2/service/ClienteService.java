package com.marari.mararimysqlv2.service;

import com.marari.mararimysqlv2.model.Cliente;
import com.marari.mararimysqlv2.model.Endereco;
import com.marari.mararimysqlv2.repository.ClienteRepository;
import com.marari.mararimysqlv2.repository.EnderecoRepository;
import com.marari.mararimysqlv2.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private VendedorRepository vendedorRepository;

    public Cliente salvar(Cliente cliente){
        enderecoRepository.save(cliente.getEndereco());
        cliente.setVendedor(vendedorRepository.findOne(cliente.getVendedor().getId()));
        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarTodos(){return clienteRepository.findAll();}

    public List<Cliente> buscarPorNome(String nome){return clienteRepository.buscaNome(nome);}

    public List<Cliente> buscarPorParametro(String nome, String nomeVendedor){
        return clienteRepository.buscaParametro(nome,nomeVendedor);
    }

    public List<Cliente> buscarPorParametro(String parametro){return clienteRepository.buscarPorParametro('%'+parametro+'%');}

    public List<Cliente> buscarPorNomeVendedor(String nomeVendedor){return clienteRepository.findByVendedorNome(nomeVendedor);}

    public List<Cliente> listaCliente(String nome,String nomeVendedor){
        if (nome != "" && nomeVendedor.equals("")){
            nome = '%'+nome+'%';
            return clienteRepository.buscaNome(nome);
        }else if (nome.equals("") && nomeVendedor != ""){
            return clienteRepository.findByVendedorNome(nomeVendedor);
        }else if (nome != "" && nomeVendedor != ""){
            return clienteRepository.findByNomeAndVendedorNome(nome,nomeVendedor);
        }else{
            return clienteRepository.findAll();
        }

//        else {
//            nome = null;
//            return clienteRepository.listaDeCliente(nome);
//        }
//        if (nomeVendedor != ""){
//            return clienteRepository.listaDeClientePorVendedor(nomeVendedor);
//        }


    }

    public void excluir(Cliente cliente){clienteRepository.delete(cliente);}

    public void editar(Cliente cliente){
        Cliente clienteExistente = clienteRepository.findOne(cliente.getId());
        if (clienteExistente != null){
            clienteRepository.save(cliente);
        }
    }

}
