package com.projetopi.prove3dsite.tabelas;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="TBDISCO")
@SequenceGenerator(name = "sqDisco", sequenceName = "sqDisco", allocationSize = 1)
public class TabelaDisco {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqDisco")
    private Long idDisco;
    
    @Column
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
