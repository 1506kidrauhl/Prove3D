package com.projetopi.prove3dapp.dadosClasses;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Component;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Gpu;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.projetopi.prove3dapp.dao.TabelaGpuDAO;
import com.projetopi.prove3dapp.dao.TabelaLogDAO;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import com.projetopi.prove3dapp.tabelas.TabelaCpu;
import com.projetopi.prove3dapp.tabelas.TabelaGpu;
import com.projetopi.prove3dapp.tabelas.TabelaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DGpu {

    @Autowired
    private TabelaGpuDAO tabelaGpuDAO;

    @Autowired
    private EnviarSlack enviarSlack;

    @Autowired
    private ApplicationController applicationController;

    public List<TabelaGpu> pegaGpu(List<TabelaGpu> gpu, TabelaComputador fkPc, TabelaUsuario fkUser) {

        TabelaGpu gpu1 = new TabelaGpu();
        List<Gpu> gpus = new ArrayList<>();

        try {
            Components component = JSensors.get.components();
            gpus = component.gpus;
        } catch (ArrayIndexOutOfBoundsException zero){
            zero.printStackTrace();
            System.out.println("Não conseguimos capturar algo da gpu");
        }

        for (final Gpu gpuComp : gpus) {
            if(gpuComp.sensors.loads.size() > 1){
                gpu1.setGpu(gpuComp.name);
                pegarDados(gpuComp, gpu1);

                gpu.add(gpu1);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(calendar.getTime());

                try {
                    Date dtHora = new Date(calendar.getTime().getTime());
                    gpu1.setDtHora(dtHora);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Deu ruim");
                }

                    gpu1.setFkComputadorG(fkPc);
                    gpu1.setFkUsuarioG(fkUser);
                    tabelaGpuDAO.save(gpu1);
                }

        }

        return gpu;

    }

    private static void pegarDados(Component component, TabelaGpu gpu) {
        if (component.sensors != null) {
            List<Temperature> temps = component.sensors.temperatures;

            if (temps.size() != 0) {

                for (final Temperature temp : temps) {
                    gpu.setTemperatura(temp.value);
                }

                if(gpu.getTemperatura() == null){
                    gpu.setTemperatura(0.0);
                }
            }

            List<Load> loads = component.sensors.loads;
            
            
            if (loads.size() != 0) {
                for (final Load load : loads) {

                    if (load.name.equals("Load GPU Core")) {
                        gpu.setUtilizacao(load.value);
                    } else if (load.name.equals("Load GPU Memory Controller")) {
                        gpu.setControlMemoria(load.value);
                    } else if (load.name.equals("Load GPU Video Engine")) {
                        gpu.setVideo(load.value);
                    } else {
                        gpu.setMemoria(load.value);
                    }
                }
            }

        }
    }

    public void verificaDados(TabelaGpu gpu, JTextArea console, TabelaUsuario user, TabelaComputador pc) {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        String mensagem = "";

        if(gpu.getTemperatura() >= 60){

            mensagem = String.format(" - Sua GPU está acima da temperatura recomendada "
                    + "(Temperatura: %.2fC, recomendado: 60C)\n", gpu.getTemperatura());
            //Printando log
            console.setText(console.getText() + formato.format(calendar.getTime()) + mensagem);

            if(gpu.getTemperatura() >= 65){
                mensagem = String.format("Sua placa de video esta com uma temperatura "
                        + "muito alta (%.2fC)", gpu.getTemperatura());
                try {
                    //Função para enviar para slack
                    enviarSlack.enviarMsg(user.getNome(), mensagem);

                    //Populando tabela Log em caso de erro
                    applicationController.enviarLog(pc, user, "GPU", "Erro",
                            "Temperatura da placa de vídeo muito acima do recomendado");

                } catch (IOException io) {
                    io.printStackTrace();
                    calendar.setTime(calendar.getTime());
                    console.setText(console.getText() + formato.format(calendar.getTime())
                            + " - Ocorreu um erro ao enviar o alerta de temperatura da GPU ao Slack \n");
                }

            } else {
                //Populando tabela Log em caso da temperatura estiver alta, mas
                //não acima de 50
                applicationController.enviarLog(pc, user, "GPU", "Alerta",
                        "Temperatura da placa de video está acima do recomendado e aumentando");
            }

        } else {
            //Caso esteja tudo OK com temperatura
            applicationController.enviarLog(pc, user, "GPU", "OK",
                    "Temperatura da placa de vídeo OK");
        }

        if(gpu.getMemoria() > 20){

            calendar.setTime(calendar.getTime());

            mensagem = String.format(" - Utilização de sua GPU está aumentando (Uso: $.2f%%)\n",
                    gpu.getMemoria());

            //Printando log
            console.setText(console.getText() + formato.format(calendar.getTime()) + mensagem);

            if(gpu.getMemoria() >= 40){

                mensagem = String.format("Sua placa de video esta com uma utilização muito alta "
                        + "muito alta (%.2f%%)", gpu.getMemoria());
                try {
                    //Função para enviar para slack
                    enviarSlack.enviarMsg(user.getNome(), mensagem);

                    //Populando tabela Log em caso de erro
                    applicationController.enviarLog(pc, user, "GPU", "Erro",
                            "Utilização da GPU aumentando consideravelmente");

                } catch (IOException io) {
                    io.printStackTrace();
                    calendar.setTime(calendar.getTime());
                    console.setText(console.getText() + formato.format(calendar.getTime())
                            + " - Ocorreu um erro ao enviar o alerta de utilização da GPU ao Slack \n");
                }

            } else {
                //Populando tabela Log em caso da temperatura estiver alta, mas
                //não acima de 50
                applicationController.enviarLog(pc, user, "GPU", "Alerta",
                        "Utilização da placa de video vem aumentando e pode causar problemas de desempenho");
            }

        }

    }

}
