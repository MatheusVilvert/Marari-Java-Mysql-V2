package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento,Integer> {

    public List<FormaPagamento> findByDescricao(String descricao);

    public List<FormaPagamento> findById(Integer id);
}
