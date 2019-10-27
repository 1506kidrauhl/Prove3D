/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetopi.prove3dapp.tabelas;

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
@Table(name = "TBLOG")
public class TabelaLog {

    @Id
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
