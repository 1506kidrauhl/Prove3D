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
import com.projetopi.prove3dapp.tabelas.TabelaGpu;
import com.projetopi.prove3dapp.tabelas.TabelaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class DGpu {

    @Autowired
    private TabelaGpuDAO tabelaGpuDAO;

    @Autowired
    private TabelaLogDAO tabelaLogDAO;

    public List<TabelaGpu> pegaGpu(List<TabelaGpu> gpu, TabelaComputador fkPc, TabelaUsuario fkUser) {

        Map<String, String> overriddenConfig = new HashMap<String, String>();

        Components component = JSensors.get.components();

        TabelaGpu gpu1 = new TabelaGpu();

        List<Gpu> gpus = component.gpus;

        for (final Gpu gpuComp : gpus) {
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

        return gpu;

    }

    private static void pegarDados(Component component, TabelaGpu gpu) {
        if (component.sensors != null) {
            List<Temperature> temps = component.sensors.temperatures;

            if (temps.size() != 0) {

                for (final Temperature temp : temps) {
                    gpu.setTemperatura(temp.value);

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



}
