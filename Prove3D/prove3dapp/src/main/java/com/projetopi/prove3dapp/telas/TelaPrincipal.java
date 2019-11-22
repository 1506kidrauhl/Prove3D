package com.projetopi.prove3dapp.telas;

import com.projetopi.prove3dapp.Config;
import com.projetopi.prove3dapp.dadosClasses.Cpu;
import com.projetopi.prove3dapp.dadosClasses.DGpu;
import com.projetopi.prove3dapp.dadosClasses.Disco;
import com.projetopi.prove3dapp.dadosClasses.Memoria;
import com.projetopi.prove3dapp.dadosClasses.Processos;
import com.projetopi.prove3dapp.tabelas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


@Component
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
    }

    @Autowired
    private Config config;

    @Autowired
    private Cpu cpu;

    @Autowired
    private Disco disco;

    @Autowired
    private DGpu gpu;

    @Autowired
    private Memoria memoria;

    @Autowired
    private Processos processos;

    public TabelaUsuario idUser;
    public TabelaComputador idComputador;
    
    SimpleDateFormat formato;
    Calendar calendar;
    
    /*Criando uma instancia de Tempo no java. Essa instância irá chamar o
    método 'ChamarRelogio()' a cada cinco segundos*/
    Timer timer = new Timer(5 * 1000, new ChamarRelogio());
    Timer timerGPU = new Timer( 15 * 5000, new ChamarGpu());
    Timer init = new Timer(1000, new TempoAtividade());

    TelaEstatisticas telaEstatisticas;
    TelaProcessos telaProcessos;
    TelaGpu telaGpu;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnProcessos = new javax.swing.JButton();
        btnEstatisticas = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblProcessos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblTempAtividade = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnRelatorios = new javax.swing.JButton();
        btnGpu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal");

        jPanel1.setBackground(new java.awt.Color(199, 209, 217));

        btnProcessos.setBackground(new java.awt.Color(0, 51, 204));
        btnProcessos.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnProcessos.setForeground(new java.awt.Color(255, 255, 255));
        btnProcessos.setText("PROCESSOS");
        btnProcessos.setBorder(null);
        btnProcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessosActionPerformed(evt);
            }
        });

        btnEstatisticas.setBackground(new java.awt.Color(0, 51, 204));
        btnEstatisticas.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnEstatisticas.setForeground(new java.awt.Color(255, 255, 255));
        btnEstatisticas.setText("ESTÁTISTICA");
        btnEstatisticas.setBorder(null);
        btnEstatisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstatisticasActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(253, 253, 253));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("PROCESSOS");

        lblProcessos.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblProcessos.setForeground(new java.awt.Color(51, 51, 255));
        lblProcessos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblProcessos.setText("100");

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("TEMPO CPU");

        lblTempAtividade.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTempAtividade.setForeground(new java.awt.Color(51, 51, 255));
        lblTempAtividade.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTempAtividade.setText("100");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblProcessos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(52, 52, 52)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTempAtividade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(lblTempAtividade)
                    .addComponent(jLabel1)
                    .addComponent(lblProcessos)
                    .addComponent(jLabel3))
                .addGap(28, 28, 28))
        );

        btnRelatorios.setBackground(new java.awt.Color(0, 51, 204));
        btnRelatorios.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnRelatorios.setForeground(new java.awt.Color(255, 255, 255));
        btnRelatorios.setText("RELATÓRIOS");
        btnRelatorios.setBorder(null);
        btnRelatorios.setMaximumSize(new java.awt.Dimension(98, 32));
        btnRelatorios.setMinimumSize(new java.awt.Dimension(98, 32));
        btnRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatoriosActionPerformed(evt);
            }
        });

        btnGpu.setBackground(new java.awt.Color(0, 51, 204));
        btnGpu.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnGpu.setForeground(new java.awt.Color(255, 255, 255));
        btnGpu.setText("DADOS GPU");
        btnGpu.setBorder(null);
        btnGpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGpuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(btnProcessos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnEstatisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnGpu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProcessos, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstatisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGpu, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Início", jPanel1);

        txtLog.setEditable(false);
        txtLog.setColumns(20);
        txtLog.setLineWrap(true);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        jTabbedPane1.addTab("Log", jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEstatisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstatisticasActionPerformed
        telaEstatisticas.idUsuario = this.idUser;
        telaEstatisticas.idComputador = this.idComputador;
        telaEstatisticas.setVisible(true);
    }//GEN-LAST:event_btnEstatisticasActionPerformed

    private void btnProcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessosActionPerformed
        telaProcessos.idComputador = this.idComputador;
        telaProcessos.idUsuario = this.idUser;
        telaProcessos.disparaRelogio();
        telaProcessos.setVisible(true);
    }//GEN-LAST:event_btnProcessosActionPerformed

    private void btnRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatoriosActionPerformed
        TelaRelatórios telaRelatorios = config.telaRelatorios();
        telaRelatorios.idUser = this.idUser;
        telaRelatorios.idComputador = this.idComputador;
        telaRelatorios.setVisible(true);
    }//GEN-LAST:event_btnRelatoriosActionPerformed

    private void btnGpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGpuActionPerformed
        telaGpu.idUsuario = this.idUser;
        telaGpu.idComputador = this.idComputador;
        telaGpu.setVisible(true);
    }//GEN-LAST:event_btnGpuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    public void dados() {
        SystemInfo si = config.oshi();
        //informações de sistema 
        OperatingSystem os = si.getOperatingSystem();

        //traz o número total de processos
        os.getProcessCount();
        lblProcessos.setText(String.valueOf(os.getProcessCount()));

        String tempo = FormatUtil.formatElapsedSecs(os.getSystemUptime()).split(",")[1];
        lblTempAtividade.setText(tempo);

        telaEstatisticas = config.telaEstatiscas();
        telaProcessos = config.telaProcessos();
        telaGpu = config.telaGpu();
    }

    public void disparaRelogio() { 
        formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault());
        calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
               
        txtLog.setWrapStyleWord(true);
        txtLog.setText(formato.format(calendar.getTime()) + " - Iniciando Sistema...\n");
        // Inicia o timer, para que a cada 5 seg, ele se repita
        timer.start();
        init.start();
        timerGPU.start();
        txtLog.setText(txtLog.getText() + formato.format(calendar.getTime()) + " - Provë 3D pronto\n");
    }

    class ChamarGpu implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            List<TabelaGpu> dadosGpu = new ArrayList<>();

            gpu.pegaGpu(dadosGpu, idComputador, idUser);
            if(!dadosGpu.isEmpty()){
                gpu.verificaDados(dadosGpu.get(0), txtLog, idUser, idComputador);
            }

            telaGpu.dadosGpu = dadosGpu;
            telaGpu.pegaGpu();
        }

    }
    
    class TempoAtividade implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            //Chamando o método que irá pegar os processos do sistema
            dados();
        }
        
    }

    class ChamarRelogio implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            /*
            //Coleta de dados da CPU
            TabelaCpu dadosCpu = new TabelaCpu();
            cpu.pegaCpu(dadosCpu, idComputador);
            cpu.verificaDados(dadosCpu, txtLog, idUser, idComputador);
            
            telaEstatisticas.cpuModelo = dadosCpu.getModelo();
            telaEstatisticas.cpuProcessos = dadosCpu.getProcessos().toString();
            if (dadosCpu.getTemperatura() < 1) {
                telaEstatisticas.cpuTemp = "Necessário permissão Admin";
            } else {
                telaEstatisticas.cpuTemp = dadosCpu.getTemperatura().toString();
            }
            
            telaEstatisticas.cpuVoltagem = dadosCpu.getVoltagem().toString();
            telaEstatisticas.cpuUtilizacao = dadosCpu.getUtilizacao();
            telaEstatisticas.cpuTempAtividade = dadosCpu.getTempAtividade().toString().split(" ")[3];
            telaEstatisticas.pegaCpu();

            
            //Coleta dados do Disco
            List<String> data = new ArrayList<>();
            TabelaDisco dadosDisco = new TabelaDisco();
            disco.pegaDisco(dadosDisco, idComputador, data);
            telaEstatisticas.discoModelo = dadosDisco.getModelo();
            telaEstatisticas.discoVLeitura = data.get(0);
            telaEstatisticas.discoVGravacao = data.get(1);
            telaEstatisticas.pegaDisco();
           // disco.verificaDados(dadosDisco, txtLog);
            
            
            //Coleta dados da memória
            TabelaMemoria dadosMemoria = new TabelaMemoria();
            memoria.pegaMemoria(dadosMemoria, idComputador, data);

            telaEstatisticas.memoriaDisponivel = data.get(0);
            telaEstatisticas.memoriaCache = data.get(1);
            telaEstatisticas.memoriaUso = data.get(2);
            telaEstatisticas.memoriaModelo = data.get(0);
            telaEstatisticas.pegaMemoria();
            //memoria.verificaDados(dadosMemoria, txtLog);
            
            //Coleta dados dos processos
            List<TabelaProcessos> dadosProcesso = new ArrayList<>();
            processos.pegaProcessos(dadosProcesso, true, idComputador, idUser, OperatingSystem.ProcessSort.CPU);
            processos.verificaDados(dadosProcesso, txtLog, idUser, idComputador);
*/
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEstatisticas;
    private javax.swing.JButton btnGpu;
    private javax.swing.JButton btnProcessos;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblProcessos;
    private javax.swing.JLabel lblTempAtividade;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables
}
