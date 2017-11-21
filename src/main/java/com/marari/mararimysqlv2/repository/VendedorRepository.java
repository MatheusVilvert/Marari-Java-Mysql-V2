package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor,Integer> {

    public Vendedor findById(Integer id);
}
