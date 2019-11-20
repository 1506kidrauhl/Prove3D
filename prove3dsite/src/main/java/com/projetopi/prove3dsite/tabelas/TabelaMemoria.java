
package com.projetopi.prove3dsite.tabelas;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="TBMEMORIA")
public class TabelaMemoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqMemory")
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
    
}
