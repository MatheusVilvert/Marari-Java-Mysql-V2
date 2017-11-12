package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    List<Usuario> findByNome(String nome);

    Usuario findByEmail(String email);

    Usuario findBySenha(String senha);

    Usuario findByEmailAndSenha(String email, String senha);
}
