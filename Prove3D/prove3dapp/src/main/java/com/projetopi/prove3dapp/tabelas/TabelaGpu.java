/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetopi.prove3dapp.tabelas;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author NOSA201907
 */
@Entity
@Table(name = "TBGPU")
@SequenceGenerator(name = "sqGpu", sequenceName = "sqGpu", allocationSize = 1)
public class TabelaGpu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqLog")
    private Long idGpu;
    
    @Column
    private String gpu;

    @Column(precision = 5, scale = 2)
    private Double utilizacao;

    @Column(precision = 5, scale = 2)
    private Double memoria;
    
    @Column(precision = 5, scale = 2)
    private Double temperatura;
    
    @Column(precision = 5, scale = 2)
    private Double controlMemoria;
    
    @Column(precision = 5, scale = 2)
    private Double video;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHora;
    
    @ManyToOne
    private TabelaComputador fkComputadorG;
    
    @ManyToOne
    private TabelaUsuario fkUsuarioG;

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }    

    public Long getIdGpu() {
        return idGpu;
    }

    public void setIdGpu(Long idGpu) {
        this.idGpu = idGpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }
    
    

    public Double getUtilizacao() {
        return utilizacao;
    }

    public void setUtilizacao(Double utilizacao) {
        this.utilizacao = utilizacao;
    }

    public Double getMemoria() {
        return memoria;
    }

    public void setMemoria(Double memoria) {
        this.memoria = memoria;
    }

    public Date getDtHora() {
        return dtHora;
    }

    public void setDtHora(Date dtHora) {
        this.dtHora = dtHora;
    }

    public Double getControlMemoria() {
        return controlMemoria;
    }

    public void setControlMemoria(Double controlMemoria) {
        this.controlMemoria = controlMemoria;
    }

    public Double getVideo() {
        return video;
    }

    public void setVideo(Double video) {
        this.video = video;
    }

    public TabelaComputador getFkComputadorG() {
        return fkComputadorG;
    }

    public void setFkComputadorG(TabelaComputador fkComputadorG) {
        this.fkComputadorG = fkComputadorG;
    }

    public TabelaUsuario getFkUsuarioG() {
        return fkUsuarioG;
    }

    public void setFkUsuarioG(TabelaUsuario fkUsuarioG) {
        this.fkUsuarioG = fkUsuarioG;
    }
    
}
