
package com.projetopi.prove3dapp.tabelas;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBCPU")

public class TabelaCpu {
  @Id
  private Long idCpu;
  
  @Column(precision = 5, scale = 2)
  private Double utilizacao;
  
  @Column (precision = 5, scale = 2)
  private Double temperatura;
  
  @Column (precision = 5, scale = 2)
  private Double voltagem;
  
  @Column 
  @Temporal(TemporalType.TIME)
  private Date tempAtividade;
  
  @Column
  @Temporal (TemporalType.TIMESTAMP)
  private Date dtHora;
  
  @OneToOne
  private TabelaComputador fkComputadorCPU;
  
  @OneToMany(mappedBy = "fkCpuP")
  private Set<TabelaProcessos> fkCpuP = new HashSet<>();

    public Set<TabelaProcessos> getFkCpu() {
        return fkCpuP;
    }

    public void setFkCpu(Set<TabelaProcessos> fkCpu) {
        this.fkCpuP = fkCpu;
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
