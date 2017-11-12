package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    @Query("SELECT p.codBarras,p.descricao,p.qtdEstoque,p.precoVenda FROM Produto p " +
            "WHERE p.codBarras LIKE lower(:parametro) OR p.descricao LIKE lower(:parametro) OR p.fornecedor.nome LIKE lower(:parametro)")
    public List<Produto> listaDePrecos(@Param("parametro") String parametro);

    @Query("SELECT p.codBarras,p.descricao,p.qtdEstoque,p.precoVenda,p.qtdEstoque,p.qtdMinEstoque,p.precoCusto,p.precoMaxVenda,p.precoMinVenda,p.precoVenda FROM Produto p " +
            "WHERE lower(p.codBarras) LIKE lower(:parametro) OR lower(p.descricao) LIKE lower(:parametro) OR lower(p.fornecedor.nome) LIKE lower(:parametro)")
    public List<Produto> posicaoEstoque(@Param("parametro") String parametro);
}
