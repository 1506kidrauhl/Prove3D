package com.projetopi.prove3dsite.controller;


import com.projetopi.prove3dsite.dao.TabelaComputadorDAO;
import com.projetopi.prove3dsite.dao.TabelaGpuDAO;
import com.projetopi.prove3dsite.dao.TabelaLogDAO;
import com.projetopi.prove3dsite.dao.TabelaUsuarioDAO;
import com.projetopi.prove3dsite.tabelas.TabelaComputador;
import com.projetopi.prove3dsite.tabelas.TabelaLog;
import com.projetopi.prove3dsite.tabelas.TabelaUsuario;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/principal")
    public String pagePrincipal(Model model){
            return "/pagePrincipal";
    }

    @GetMapping(value = {"/index", "/", ""})
    public String pageIndex(Model model){
            return "/index";
    }

    @GetMapping("/dashboard")
    public String pageDash(Model model){
            return "/dashboard";
    }

    @GetMapping("/relatorio")
    public String pageRelatorio(Model model){
            return "/pageLog";
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<TabelaUsuario> loginUser(@RequestParam("login") String login,
                                                                 @RequestParam("senha") String senha, HttpSession session){
        TabelaUsuario user = tabelaUsuarioDAO.findByLogin(login, senha);

        if(user == null){
            return new ResponseEntity("Login ou senha incorretos", HttpStatus.PRECONDITION_FAILED);
        } else{
            session.setAttribute("dadosUser", user);
            TabelaComputador pc = new TabelaComputador();

            Object pcD = tabelaComputadorDAO.findData(user.getIdUsuario());

            if(pcD != null){
                pc.setIdComputador(Math.round(Double.valueOf(((Object[])pcD)[0].toString())));
                pc.setSistemaOperacional(((Object[])pcD)[1].toString());
                pc.setNmComputador(((Object[])pcD)[2].toString());
                pc.setModelo(((Object[])pcD)[3].toString());

                session.setAttribute("dadosPc", pc);
            }

            return new ResponseEntity("/principal", HttpStatus.OK);
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
    public @ResponseBody ResponseEntity<String> editarPerfil(@RequestParam("id") Long id, @RequestParam("nome") String nome,
                                                             @RequestParam("login") String login, @RequestParam("senha") String senha,
                                                             @RequestParam("email") String email, @RequestParam("telefone") String telefone,
                                                             @RequestParam("cpf") String cpf){

        TabelaUsuario editar = new TabelaUsuario();
        tabelaUsuarioDAO.findById(id);

        editar.setIdUsuario(id);
        editar.setNome(nome);
        editar.setLogin(login);
        editar.setSenha(senha);
        editar.setEmail(email);
        editar.setTelefone(telefone);
        editar.setCpf(cpf);

        try{
            tabelaUsuarioDAO.save(editar);
            return new ResponseEntity("Dados alterados com sucesso", HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity("Não foi possível editar seus dados, por favor, tente novamente", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/pegarDados", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Dashaboard>> pegarDados(@RequestParam("componente") Integer componente,@RequestParam("filtro")String filtro,
                                       @RequestParam("id")Long idUser, @RequestParam("idPc") Long dadosComputador){

        List<Dashaboard> dadosDash = new ArrayList<>();

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
            Object[] dadoGpu = tabelaGpuDAO.filtraGPU(idUser);
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

        return new ResponseEntity(dadosDash.toString(), HttpStatus.OK);

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
        Object[] dadoLog;

        try{
            dadoLog = (Object[]) tabelaLogDAO.findLastLog(componente, id, fil)[0];

            dados.setComponente(dadoLog[1].toString());
            dados.setDescricao(dadoLog[2].toString());
            dados.setTipo(dadoLog[4].toString());

        } catch (InvalidDataAccessResourceUsageException sql){
            sql.printStackTrace();
            System.out.println("Erro de sintaxe");
        }catch (ArrayIndexOutOfBoundsException zero){
            System.out.println("Nenhum dado encontrado durante a pesquisa");
        } catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Ocorreu um erro durante a pesquisa");
        }

        return dados;

    }

}
