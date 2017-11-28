package com.marari.mararimysqlv2.service;

import com.marari.mararimysqlv2.model.Vendedor;
import com.marari.mararimysqlv2.repository.EnderecoRepository;
import com.marari.mararimysqlv2.repository.PerfilRepository;
import com.marari.mararimysqlv2.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private PerfilRepository perfilRepository;


    public Vendedor salvar(Vendedor vendedor){
        vendedor.setPerfil(perfilRepository.findOne(2));
        return vendedorRepository.save(vendedor);
    }

    public List<Vendedor> buscarTodos(){return vendedorRepository.findAll();}

    public void excluir(Vendedor vendedor){vendedorRepository.delete(vendedor);}

    public void editar(Vendedor vendedor){
        Vendedor vendedorExistente = vendedorRepository.findOne(vendedor.getId());
        if (vendedorExistente != null){
            vendedorRepository.save(vendedor);
        }
    }

    public Vendedor buscarPorId(Integer id){return vendedorRepository.findById(id);}
}
