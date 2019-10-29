
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
@Table(name="TBMEMORIA")
public class TabelaMemoria {
    @Id
    private Long idMemoria;
    
    @Column(precision=2,scale=1)
    private Double emUso;
    
    @Column(precision=2,scale=1)
    private Double disponivel;
    
    @Column(precision = 5, scale = 2)
    private Double cache;
        
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHora;
    
    @OneToOne
    private TabelaComputador fkComputadorM;

    public Long getIdMemoria() {
        return idMemoria;
    }

    public void setIdMemoria(Long idMemoria) {
        this.idMemoria = idMemoria;
    }

    public Double getEmUso() {
        return emUso;
    }

    public void setEmUso(Double emUso) {
        this.emUso = emUso;
    }

    public Double getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Double disponivel) {
        this.disponivel = disponivel;
    }

    public Double getCache() {
        return cache;
    }

    public void setCache(Double cache) {
        this.cache = cache;
    }

    public TabelaComputador getFkComputadorM() {
        return fkComputadorM;
    }

    public void setFkComputadorM(TabelaComputador fkComputadorM) {
        this.fkComputadorM = fkComputadorM;
    }

    public Date getDtHora() {
        return dtHora;
    }

    public void setDtHora(Date dtHora) {
        this.dtHora = dtHora;
    }

    public TabelaComputador getFkComputador() {
        return fkComputadorM;
    }

    public void setFkComputador(TabelaComputador fkComputador) {
        this.fkComputadorM = fkComputador;
    }
    
}
