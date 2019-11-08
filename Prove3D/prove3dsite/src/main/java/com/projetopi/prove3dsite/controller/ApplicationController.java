package com.projetopi.prove3dsite.controller;


import com.projetopi.prove3dsite.dao.TabelaComputadorDAO;
import com.projetopi.prove3dsite.dao.TabelaGpuDAO;
import com.projetopi.prove3dsite.dao.TabelaLogDAO;
import com.projetopi.prove3dsite.dao.TabelaUsuarioDAO;
import com.projetopi.prove3dsite.tabelas.TabelaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController {

    
    @Autowired
    private TabelaUsuarioDAO tabelaUsuarioDAO;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private TabelaComputadorDAO tabelaComputadorDAO;

    @Autowired
    private TabelaGpuDAO tabelaGpuDAO;

    @Autowired
    private TabelaLogDAO tabelaLogDAO;

    String log, pass;

    TabelaUsuario dadosUser;

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
            dadosUser = dados;
            return "redirect:/principal";
        }
        
    }

    @RequestMapping(value = "/enviarEmail",method = RequestMethod.POST)
    public @ResponseBody String enviarEmail(@RequestBody DadosEmail mail){

        SimpleMailMessage mailMessage=new SimpleMailMessage();

        mailMessage.setTo("support@prove.zendesk.com");
        mailMessage.setSubject(mail.getTipo() +" - "+ mail.getAssunto() + " - " + mail.getNome());
        mailMessage.setText(mail.getEmail() + ".\n" + mail.getMensagem());
        mailMessage.setFrom(mail.getEmail());
        try{
            javaMailSender.send(mailMessage);
            return "Seu chamado foi aberto. Obrigado pelo feedback!";
        }catch (Exception ex ){
            ex.printStackTrace();
            return "Erro ao enviar o email. Ops, por favor, tente abrir novamente o chamado!";
        }

    }

    @RequestMapping(value = "/editarPerfil", method = RequestMethod.POST)
    public @ResponseBody String editarPerfil(@RequestBody TabelaUsuario perfil){

        TabelaUsuario editar = new TabelaUsuario();
        tabelaUsuarioDAO.findById(perfil.getIdUsuario());

        editar.setIdUsuario(perfil.getIdUsuario());
        editar.setNome(perfil.getNome());
        editar.setLogin(perfil.getLogin());
        editar.setSenha(perfil.getSenha());
        editar.setEmail(perfil.getEmail());
        editar.setTelefone(perfil.getTelefone());
        editar.setCpf(perfil.getCpf());

        try{
            tabelaUsuarioDAO.save(editar);
            log = perfil.getLogin();
            pass = perfil.getSenha();
            return "Sucesso!. Seus dados foram alterados com sucesso.";
        } catch (Exception ex){
            ex.printStackTrace();
            return "Erro!. Não foi possível editar seus dados, por favor, tente novamente";
        }
    }

}
