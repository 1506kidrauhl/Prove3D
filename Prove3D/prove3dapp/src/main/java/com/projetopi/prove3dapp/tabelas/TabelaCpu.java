package com.projetopi.prove3dapp.tabelas;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "TBCPU")
@SequenceGenerator(name = "sqCpu", sequenceName = "sqCpu", allocationSize = 1)
public class TabelaCpu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqCpu")
    private Long idCpu;

    @Column(precision = 5, scale = 2)
    private Double utilizacao;

    @Column(precision = 5, scale = 2)
    private Double temperatura;

    @Column(precision = 5, scale = 2)
    private Double voltagem;

    @Column
    @Temporal(TemporalType.TIME)
    private Date tempAtividade;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHora;

    @Column
    private Integer processos;
    
    @Column
    private String modelo;

    @OneToOne
    private TabelaComputador fkComputadorCPU;

    public Integer getProcessos() {
        return processos;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setProcessos(Integer processos) {
        this.processos = processos;
    }

    public TabelaComputador getFkComputadorCPU() {
        return fkComputadorCPU;
    }

    public void setFkComputadorCPU(TabelaComputador fkComputadorCPU) {
        this.fkComputadorCPU = fkComputadorCPU;
    }

    public Long getIdCpu() {
        return idCpu;
    }

    public void setIdCpu(Long idCpu) {
        this.idCpu = idCpu;
    }

    public Double getUtilizacao() {
        return utilizacao;
    }

    public void setUtilizacao(Double utilizacao) {
        this.utilizacao = utilizacao;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(Double voltagem) {
        this.voltagem = voltagem;
    }

    public Date getTempAtividade() {
        return tempAtividade;
    }

    public void setTempAtividade(Date tempAtividade) {
        this.tempAtividade = tempAtividade;
    }

    public Date getDtHora() {
        return dtHora;
    }

    public void setDtHora(Date dtHora) {
        this.dtHora = dtHora;
    }

    public TabelaComputador getFkComputador() {
        return fkComputadorCPU;
    }

    public void setFkComputador(TabelaComputador fkComputador) {
        this.fkComputadorCPU = fkComputador;
    }

}
