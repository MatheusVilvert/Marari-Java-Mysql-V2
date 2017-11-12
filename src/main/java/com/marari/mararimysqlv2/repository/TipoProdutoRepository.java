package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto,Integer> {

    public List<TipoProduto> findByDescricao(String descricao);

}
