package com.marari.mararimysqlv2.service;

import com.marari.mararimysqlv2.model.Perfil;
import com.marari.mararimysqlv2.model.Usuario;
import com.marari.mararimysqlv2.repository.PerfilRepository;
import com.marari.mararimysqlv2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    public Usuario salvar(Usuario usuario){
        String nomeResgistrado="";
        String nomeNovo = usuario.getNome();

        if (usuario.getPerfil() == null) {
            Perfil perfilPadrao = perfilRepository.findOne(2);
            usuario.setPerfil(perfilPadrao);
        }

        if (usuario.getId()!= null){
            Usuario usuRegistrado = usuarioRepository.findOne(usuario.getId());
            nomeResgistrado = usuRegistrado.getNome();
        }

        if (!nomeResgistrado.equals(nomeNovo)){
            List<Usuario> usuarioList = usuarioRepository.findByNome(usuario.getNome());
            /*if (usuarioList != null && usuarioList.size() > 0){
                throw new ServiceException ("Usuario Já Existente");
            }*/
        }
        //para casdastrar usuario com perfil ja existente passe o id do perfil, caso queira cadastrar usuario e ao mesmo tempo
        //um novo perfil passe a descricao do perfil
//        if (usuario.getPerfil().getId() == null){
//            perfilRepository.save(usuario.getPerfil());
//        }else {
            Perfil perfil = perfilRepository.findOne(usuario.getPerfil().getId());
            usuario.setPerfil(perfil);
       // }


        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarPorNome(String nome){return usuarioRepository.findByNome(nome);}

    public void excluir (Usuario usuario){usuarioRepository.delete(usuario);}

    public List<Usuario> buscarTodos(){return usuarioRepository.findAll();}

    public List<Usuario> buscaVendedor(){return usuarioRepository.buscaVendedor();}

    public Usuario buscarPorEmail(String email){return usuarioRepository.findByEmail(email);}

    public Usuario buscarPorSenha(String senha){return usuarioRepository.findBySenha(senha);}

    public Usuario buscarPorEmailSenha(String email, String senha){return usuarioRepository.findByEmailAndSenha(email,senha);}
}
