package com.marari.mararimysqlv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")
@Controller
public class RelatorioController {

    @GetMapping("teste")
    public String teste(){
        return "teste";
    }
}
