package br.thiago.entrada_saida_pessoas.usuarios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/admin/crudUser")
    public String getAdminPage() {
        return "GerenciamentoUsuario/crudUser"; // O Spring adicionará automaticamente o prefixo e sufixo definidos
    }

    @GetMapping("/user/entrada")
    public String getUserPage() {
        return "entrada";
    }
}
