package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

   @Query("SELECT c FROM Cliente c WHERE lower(c.info) LIKE lower(:parametro) OR lower(c.nome) LIKE lower(:parametro) OR lower(c.email) LIKE lower(:parametro) OR lower(c.cpfCnpj) LIKE lower(:parametro) OR lower(c.rg) LIKE lower(:parametro) OR lower(c.telefone) LIKE lower(:parametro)" )
   List<Cliente> buscarPorParametro(@Param("parametro") String parametro);
}
