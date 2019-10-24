package com.projetopi.prove3dsite.controller;


import com.projetopi.prove3dsite.dao.TabelaUsuarioDAO;
import com.projetopi.prove3dsite.tabelas.TabelaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplicationController {

    
    @Autowired
    private TabelaUsuarioDAO tabelaUsuarioDAO;

    String log, pass;

    @GetMapping("/principal")
    public String pagePrincipal(Model model){

        TabelaUsuario dados = tabelaUsuarioDAO.findByLogin(log, pass);

        if(dados == null){
            return "redirect:/index";
        } else{
            model.addAttribute("dadosLog",dados);
            return "/pagePrincipal";
        }

    }

    @GetMapping("/index")
    public String pageIndex(Model model){

        if(log != null || pass != null){
            model.addAttribute("dadosLog", tabelaUsuarioDAO.findByLogin(log, pass));
            return "/index";
        } else {
            return "/index";
        }

    }

    @GetMapping("/dashboard")
    public String pageDash(Model model){

        TabelaUsuario dados = tabelaUsuarioDAO.findByLogin(log, pass);

        if(dados == null){
            return "redirect:/index";
        } else{
            model.addAttribute("dadosLog",dados);
            return "/dashboard";
        }

    }

    @GetMapping("/relatorio")
    public String pageRelatorio(Model model){

        TabelaUsuario dados = tabelaUsuarioDAO.findByLogin(log, pass);

        if(dados == null){
            return "redirect:/index";
        } else{
            model.addAttribute("dadosLog",dados);
            return "/pageLog";
        }

    }

    @GetMapping("/sair")
    public String efetuarLogout(){
        log = null;
        pass = null;
        return "redirect:/index";
    }

    @PostMapping("/cadastro")
    public String cadastroUser(@RequestParam("inputEmail") String email,
            @RequestParam("inputName") String nome,@RequestParam("inputLogin")
            String login,@RequestParam("inputPassword") String senha,
            @RequestParam("inputTel") String telefone,@RequestParam("inputCpf")
            String cpf, TabelaUsuario tbUser){
        
        tbUser.setEmail(email);
        tbUser.setNome(nome);
        tbUser.setLogin(login);
        tbUser.setSenha(senha);
        tbUser.setTelefone(telefone);
        tbUser.setCpf(cpf);
        
        tabelaUsuarioDAO.save(tbUser);
        
        return "/index";
        
    }
    
    @GetMapping("/login")
    public String loginUser(@RequestParam("txtLogin") String login, 
            @RequestParam("txtSenha") String senha, Model model){
        
        TabelaUsuario dados = tabelaUsuarioDAO.findByLogin(login, senha);
        
        if(dados == null){
            model.addAttribute("verificacao",0);
            return "/index";
        } else{
            log = login;
            pass = senha;
            return "redirect:/principal";
        }
        
    }

}
