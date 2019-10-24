package com.projetopi.prove3dapp.tabelas;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TBDISCO")
public class TabelaDisco {
    @Id
    private Long idDisco;
    
    @Column(length=45)
    private String modelo;
    
    @Column(precision=4,scale=2)
    private Double vLeitura;
    
    @Column(precision=4,scale=2)
    private Double vGravacao;
    
    @Column 
    private Integer tempAtividade;
    
    @Column(precision=4, scale=2)
    private Double tempResp;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHora;
    
    @OneToOne
    private TabelaComputador fkComputadorD;

    public Long getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(Long idDisco) {
        this.idDisco = idDisco;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getvLeitura() {
        return vLeitura;
    }

    public void setvLeitura(Double vLeitura) {
        this.vLeitura = vLeitura;
    }

    public Double getvGravacao() {
        return vGravacao;
    }

    public void setvGravacao(Double vGravacao) {
        this.vGravacao = vGravacao;
    }

    public Integer getTempAtividade() {
        return tempAtividade;
    }

    public void setTempAtividade(Integer tempAtividade) {
        this.tempAtividade = tempAtividade;
    }

    public Double getTempResp() {
        return tempResp;
    }

    public void setTempResp(Double tempResp) {
        this.tempResp = tempResp;
    }

    public Date getDtHora() {
        return dtHora;
    }

    public void setDtHora(Date dtHora) {
        this.dtHora = dtHora;
    }

    public TabelaComputador getFkComputador() {
        return fkComputadorD;
    }

    public void setFkComputador(TabelaComputador fkComputador) {
        this.fkComputadorD = fkComputador;
    }
    
    
    
    
    
    
    
}
