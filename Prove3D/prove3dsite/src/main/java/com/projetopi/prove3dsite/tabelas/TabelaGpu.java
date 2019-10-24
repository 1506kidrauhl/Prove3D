/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetopi.prove3dsite.tabelas;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author NOSA201907
 */
@Entity
@Table(name = "TBGPU")
public class TabelaGpu {

    @Id
    private Long idGpu;
    
    @Column
    private String gpu;
    
    @Column(precision = 5, scale = 2)
    private Double utilizacao;

    @Column(precision = 5, scale = 2)
    private Double memoria;

    @Column(precision = 5, scale = 2)
    private Double memoriaCompartilhada;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHora;
    
    @ManyToOne
    private TabelaComputador fkComputadorG;

    @ManyToOne
    private TabelaUsuario fkUsuarioG;

    public TabelaUsuario getFkUsuarioG() {
        return fkUsuarioG;
    }

    public void setFkUsuarioG(TabelaUsuario fkUsuarioG) {
        this.fkUsuarioG = fkUsuarioG;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public TabelaComputador getFkComputadorG() {
        return fkComputadorG;
    }

    public void setFkComputadorG(TabelaComputador fkComputadorG) {
        this.fkComputadorG = fkComputadorG;
    }

    public Long getIdGpu() {
        return idGpu;
    }

    public void setIdGpu(Long idGpu) {
        this.idGpu = idGpu;
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

    public Double getMemoriaCompartilhada() {
        return memoriaCompartilhada;
    }

    public void setMemoriaCompartilhada(Double memoriaCompartilhada) {
        this.memoriaCompartilhada = memoriaCompartilhada;
    }

    public Date getDtHora() {
        return dtHora;
    }

    public void setDtHora(Date dtHora) {
        this.dtHora = dtHora;
    }

}
