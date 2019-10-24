
package com.projetopi.prove3dsite.tabelas;

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
    
    @Column 
    private Integer paginada;
    
    @Column
    private Integer nPaginada;
    
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

    public Integer getPaginada() {
        return paginada;
    }

    public void setPaginada(Integer paginada) {
        this.paginada = paginada;
    }

    public Integer getnPaginada() {
        return nPaginada;
    }

    public void setnPaginada(Integer nPaginada) {
        this.nPaginada = nPaginada;
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
