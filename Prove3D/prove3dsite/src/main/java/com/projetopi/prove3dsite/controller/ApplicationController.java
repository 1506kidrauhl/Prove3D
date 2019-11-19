package com.projetopi.prove3dsite.controller;


import com.projetopi.prove3dsite.dao.TabelaComputadorDAO;
import com.projetopi.prove3dsite.dao.TabelaGpuDAO;
import com.projetopi.prove3dsite.dao.TabelaLogDAO;
import com.projetopi.prove3dsite.dao.TabelaUsuarioDAO;
import com.projetopi.prove3dsite.tabelas.TabelaComputador;
import com.projetopi.prove3dsite.tabelas.TabelaLog;
import com.projetopi.prove3dsite.tabelas.TabelaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

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

    TabelaComputador dadosComputador;

    String log, pass;

    TabelaUsuario idUsuario;

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

    @RequestMapping(value = "/pegarDados", method = RequestMethod.GET)
    @ResponseBody
    public List<Dashaboard> pegarDados(@RequestParam("componente") Integer componente,@RequestParam("filtro")String filtro,
                                       @RequestParam("id")Long id){

        List<Dashaboard> dadosDash = new ArrayList<>();


       if(dadosComputador == null ||idUsuario == null) {
           Optional<TabelaUsuario>  user = tabelaUsuarioDAO.findById(id);
           idUsuario = user.get();

           Object[] dado = tabelaComputadorDAO.findData(id);
           Object[] data = (Object[]) dado[0];

           TabelaComputador tbComputador = new TabelaComputador();
           long idPc = Math.round(Double.valueOf(data[0].toString()));

           tbComputador.setIdComputador(idPc);

           tbComputador.setSistemaOperacional(data[1].toString());
           tbComputador.setNmComputador(data[2].toString());
           tbComputador.setModelo(data[3].toString());

           dadosComputador = tbComputador;
       }
        //Cpu = 0 Disco = 1 Gpu =2 Memoria = 3
        if (componente == 0) {

            Object[] dadoCpu = tabelaComputadorDAO.filtraCPU(dadosComputador);

            for (int i = 0; i < dadoCpu.length; i++) {
                Dashaboard dashaboard = new Dashaboard();
                Object[] dataCpu = (Object[]) dadoCpu[i];

                if (filtro.equals("temp")) {
                    dashaboard.setId(Integer.valueOf(dataCpu[0].toString()));
                    dashaboard.setDados(Double.valueOf(dataCpu[2].toString()));
                    dashaboard.setDataHora(dataCpu[3].toString());
                } else {
                    dashaboard.setId(Integer.valueOf(dataCpu[0].toString()));
                    dashaboard.setDados(Double.valueOf(dataCpu[1].toString()));
                    dashaboard.setDataHora(dataCpu[3].toString());
                }

                dadosDash.add(dashaboard);

            }

        }
        else if (componente == 1){
            Object[] dadoDisco = tabelaComputadorDAO.filtraDisco(dadosComputador);
            for (int i = 0; i < dadoDisco.length; i++) {
                Dashaboard dashaboard = new Dashaboard();
                Object[] dataDisco = (Object[]) dadoDisco[i];

                if (filtro.equals("grav")) {
                    dashaboard.setId(Integer.valueOf(dataDisco[0].toString()));
                    dashaboard.setDados(Double.valueOf(dataDisco[1].toString()));
                    dashaboard.setDataHora(dataDisco[3].toString());
                } else {
                    dashaboard.setId(Integer.valueOf(dataDisco[0].toString()));
                    dashaboard.setDados(Double.valueOf(dataDisco[2].toString()));
                    dashaboard.setDataHora(dataDisco[3].toString());
                }

                dadosDash.add(dashaboard);


            }

        }else if (componente == 2){
            Object[] dadoGpu = tabelaGpuDAO.filtraGPU(idUsuario);
            for (int i = 0; i < dadoGpu.length; i++) {
                Dashaboard dashaboard = new Dashaboard();
                Object[] dataGpu = (Object[]) dadoGpu[i];

                if (filtro.equals("util")) {
                    dashaboard.setId(Integer.valueOf(dataGpu[0].toString()));
                    dashaboard.setDados(Double.valueOf(dataGpu[1].toString()));
                    dashaboard.setDataHora(dataGpu[3].toString());
                } else {
                    dashaboard.setId(Integer.valueOf(dataGpu[0].toString()));
                    dashaboard.setDados(Double.valueOf(dataGpu[2].toString()));
                    dashaboard.setDataHora(dataGpu[3].toString());
                }

                dadosDash.add(dashaboard);


            }

        }else {
            Object[] dadoMemoria = tabelaComputadorDAO.filtraMemoria(dadosComputador);
            for (int i = 0; i < dadoMemoria.length; i++) {
                Object[] dataMemoria = (Object[]) dadoMemoria[i];
                Dashaboard dashaboard = new Dashaboard();

                dashaboard.setId(Integer.valueOf(dataMemoria[0].toString()));
                dashaboard.setDados(Double.valueOf(dataMemoria[1].toString()));
                dashaboard.setDataHora(dataMemoria[2].toString());

                dadosDash.add(dashaboard);

            }

        }

        dadosDash.sort(Comparator.comparing(Dashaboard::getId));
        return dadosDash;


    }


    @RequestMapping(value = "/gerarLog", method = RequestMethod.GET)
    @ResponseBody
    public List<TabelaLog> gerarLog(@RequestParam("dtInicio")String dtInicio, @RequestParam("dtFim") String dtFim,
                                    @RequestParam("tipo") String tipoLog, @RequestParam("comp") String componente,
                                    @RequestParam("id") Long idUser){

        List<TabelaLog> dadosLog = new ArrayList<>();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date dtI = new Date(), dtF = new Date();

        try{
            dtI = formato.parse(dtInicio + ":00");
            dtF = formato.parse(dtFim + ":59");
        } catch (Exception ex){
            System.out.println("Erro ao formatar a data");
        }
        Object[] dados;

        if(tipoLog.equals("")){
           dados =  tabelaLogDAO.findAllByInitialAndFinal(dtI, dtF, componente, idUser);
        } else{
           dados =  tabelaLogDAO.findByComponente(dtI, dtF, tipoLog, componente, idUser);
        }

        for(int i = 0; i < dados.length; i++){
            Object[] data = (Object[]) dados[i];
            TabelaLog dataLog = new TabelaLog();

            dataLog.setComponente(data[0].toString());
            dataLog.setDescricao(data[1].toString());
            dataLog.setTipo(data[2].toString());

            try {
                dataLog.setDtHora(formato.parse(data[3].toString()));
            }catch (Exception ex){
                System.out.println("Erro ao formatar data");
            }

            dadosLog.add(dataLog);

        }

        return dadosLog;
    }

    @RequestMapping(value = "/lastLog", method = RequestMethod.GET)
    @ResponseBody
    public TabelaLog coletarUltimoLog(@RequestParam("comp") String componente, @RequestParam("id") Long id,
                                      @RequestParam("filtro") String fil){

        TabelaLog dados = new TabelaLog();
        Object[] dadoLog = (Object[]) tabelaLogDAO.findLastLog(componente, id, fil)[0];

        dados.setComponente(dadoLog[1].toString());
        dados.setDescricao(dadoLog[2].toString());
        dados.setTipo(dadoLog[4].toString());

        return dados;

    }


}
