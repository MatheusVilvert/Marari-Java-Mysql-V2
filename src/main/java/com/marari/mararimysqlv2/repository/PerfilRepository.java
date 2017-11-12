package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil,Integer> {

    Perfil findByDescricao(String descricao);

}
