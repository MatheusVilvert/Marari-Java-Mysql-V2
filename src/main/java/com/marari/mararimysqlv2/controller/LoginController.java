package com.marari.mararimysqlv2.controller;

import com.marari.mararimysqlv2.model.Usuario;
import com.marari.mararimysqlv2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/login")
    public Usuario autenticar (@RequestParam("email")String email, @RequestParam("senha")String senha){

            Usuario usuario = usuarioService.buscarPorEmailSenha(email,senha);

            if (usuario != null){
                return usuario;
            }else return null;
}}
