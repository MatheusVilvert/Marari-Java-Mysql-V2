package com.marari.mararimysqlv2.repository;

import com.marari.mararimysqlv2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    List<Usuario> findByNome(String nome);

    Usuario findByEmail(String email);

    Usuario findBySenha(String senha);

    Usuario findByEmailAndSenha(String email, String senha);

    //@Query("select u from Usuario u where lower( u.perfil.descricao) like 'vendedor' ")
    @Query("select u from Usuario u where  u.perfil.id = 2 ")
    List<Usuario> buscaVendedor();
}
