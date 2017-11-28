package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor,Integer> {

    @Query("SELECT f FROM Fornecedor f " +
            "WHERE lower(f.nome) LIKE lower(:parametro) OR lower(f.endereco.rua) LIKE lower(:parametro) " +
            "OR lower(f.endereco.numero) LIKE lower(:parametro) OR lower(f.endereco.bairro) LIKE lower(:parametro) OR lower(f.endereco.cep) LIKE lower(:parametro)" +
            "OR lower(f.endereco.cidade) LIKE lower(:parametro) OR lower(f.endereco.estado) LIKE lower(:parametro) OR lower(f.contato) LIKE lower(:parametro)" +
            "OR lower(f.email) LIKE lower(:parametro) OR lower(f.info) LIKE lower(:parametro) ")
    public List<Fornecedor> listaPorParametro(@Param("parametro") String parametro);


    @Query("select f from Fornecedor f where f.nome like lower(:nome) ")
    public List<Fornecedor>buscaPorNome(@Param("nome")String nome);
}
