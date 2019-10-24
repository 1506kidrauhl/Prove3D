/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetopi.prove3dapp.tabelas;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author NOSA201907
 */
@Entity
@Table(name = "TBCOMPUTADOR")
public class TabelaComputador {
   @Id
   private Long idComputador;
   
   @Column(length = 50)
   private String sistemOperacional;
   
   @Column(length = 45)
   private String nmComputador;
   
   @Column(length = 45)
   private String modelo;
  
   @ManyToOne
   private TabelaUsuario fkUsuario;
   
   @OneToOne(mappedBy = "fkComputadorCPU")
   private TabelaCpu fkComputadorCPU;
   
   @OneToOne(mappedBy="fkComputadorM")
   private TabelaMemoria fkComputadorM;
   
   @OneToOne(mappedBy="fkComputadorD")
   private TabelaDisco fkComputadorD;
   
   @OneToMany(mappedBy= "fkComputadorL")
   private Set<TabelaLog>fkComputadorL= new HashSet<>();

    public Set<TabelaLog> getFkComputadorL() {
        return fkComputadorL;
    }

    public void setFkComputadorL(Set<TabelaLog> fkComputadorL) {
        this.fkComputadorL = fkComputadorL;
    }
 

    public TabelaMemoria getFkComputadorM() {
        return fkComputadorM;
    }

    public void setFkComputadorM(TabelaMemoria fkComputadorM) {
        this.fkComputadorM = fkComputadorM;
    }
   

    public TabelaCpu getFkComputadorCPU() {
        return fkComputadorCPU;
    }

    public void setFkComputadorCPU(TabelaCpu fkComputadorCPU) {
        this.fkComputadorCPU = fkComputadorCPU;
    }

   
   
   public Long getIdComputador() {
        return idComputador;
    }

    public void setIdComputador(Long idComputador) {
        this.idComputador = idComputador;
    }

    public String getSistemOperacional() {
        return sistemOperacional;
    }

    public void setSistemOperacional(String sistemOperacional) {
        this.sistemOperacional = sistemOperacional;
    }

    public String getNmComputador() {
        return nmComputador;
    }

    public void setNmComputador(String nmComputador) {
        this.nmComputador = nmComputador;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public TabelaUsuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(TabelaUsuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
   
   
   
   
   
  }
