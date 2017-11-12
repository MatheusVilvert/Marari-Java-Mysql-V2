package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Usuario;
import com.marari.mararimysqlv2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public Usuario autenticar (@RequestParam("email")String email, @RequestParam("senha")String senha){
        try {
            Usuario usuario = usuarioService.buscarPorEmailSenha(email,senha);

            if (usuario != null){
                return usuario;
            }else {
                throw new IllegalArgumentException();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
