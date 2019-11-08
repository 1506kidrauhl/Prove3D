/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetopi.prove3dsite.tabelas;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author NOSA201907
 */
@Entity
@Table(name = "TBLOG")
@SequenceGenerator(name = "sqLog", sequenceName = "sqLog", allocationSize = 1)
public class TabelaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqLog")
    private Long idLog;

    @Column(length = 45)
    private String tipo;

    @Column(length = 15)
    private String componente;

    @Column(length = 255)
    private String descricao;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHora;

    @ManyToOne
    private TabelaComputador fkComputadorL;

    @ManyToOne
    private TabelaUsuario fkUsuario;

    public Long getIdLog() {
        return idLog;
    }

    public void setIdLog(Long idLog) {
        this.idLog = idLog;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtHora() {
        return dtHora;
    }

    public void setDtHora(Date dtHora) {
        this.dtHora = dtHora;
    }

    public TabelaComputador getFkComputador() {
        return fkComputadorL;
    }

    public void setFkComputador(TabelaComputador fkComputador) {
        this.fkComputadorL = fkComputador;
    }

    public TabelaUsuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(TabelaUsuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

}
