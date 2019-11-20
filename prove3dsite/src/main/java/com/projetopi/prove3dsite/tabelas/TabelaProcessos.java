
package com.projetopi.prove3dsite.tabelas;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "TBPROCESSOS")
public class TabelaProcessos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqProc")
    private Long idProcesso;
    
    @Column
    private Integer pid;
    
    @Column(length = 45)
    private String processo;
    
    @Column 
    private Integer prioridade;
    
    @Column
    @Temporal(TemporalType.TIME)
    private Date tempoAtividade;
    
    @Column(precision = 5, scale = 2)
    private Double usoCpu;
    
    @Column(precision = 5 , scale = 2)
    private Double usoMemoria;
    
    @Column 
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;
    
    @ManyToOne
    private TabelaComputador fkComputadorP;
    
    @ManyToOne
    private TabelaUsuario fkUsuarioP;
    
    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Date getTempoAtividade() {
        return tempoAtividade;
    }

    public void setTempoAtividade(Date tempoAtividade) {
        this.tempoAtividade = tempoAtividade;
    }

    public Double getUsoCpu() {
        return usoCpu;
    }

    public void setUsoCpu(Double usoCpu) {
        this.usoCpu = usoCpu;
    }

    public Double getUsoMemoria() {
        return usoMemoria;
    }

    public void setUsoMemoria(Double usoMemoria) {
        this.usoMemoria = usoMemoria;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public TabelaComputador getFkComputadorP() {
        return fkComputadorP;
    }

    public void setFkComputadorP(TabelaComputador fkComputadorP) {
        this.fkComputadorP = fkComputadorP;
    }

    public TabelaUsuario getFkUsuarioP() {
        return fkUsuarioP;
    }

    public void setFkUsuarioP(TabelaUsuario fkUsuarioP) {
        this.fkUsuarioP = fkUsuarioP;
    }
    
}
