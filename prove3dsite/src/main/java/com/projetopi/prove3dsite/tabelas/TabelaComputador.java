/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projetopi.prove3dsite.tabelas;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author NOSA201907
 */
@Entity
@Table(name = "TBCOMPUTADOR")
public class TabelaComputador {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqComp")
   private Long idComputador;
   
   @Column(length = 50)
   private String sistemaOperacional;
   
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
   
   @OneToMany (mappedBy="fkComputadorP")
   private Set<TabelaProcessos> fkComputadorP = new HashSet<>();
   
   @OneToMany(mappedBy= "fkComputadorL")
   private Set<TabelaLog>fkComputadorL= new HashSet<>();

   @OneToMany(mappedBy= "fkComputadorG")
   private Set<TabelaGpu>fkComputadorG= new HashSet<>();

   
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

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
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

    public TabelaDisco getFkComputadorD() {
        return fkComputadorD;
    }

    public void setFkComputadorD(TabelaDisco fkComputadorD) {
        this.fkComputadorD = fkComputadorD;
    }

    public Set<TabelaProcessos> getFkComputadorP() {
        return fkComputadorP;
    }

    public void setFkComputadorP(Set<TabelaProcessos> fkComputadorP) {
        this.fkComputadorP = fkComputadorP;
    }

    public Set<TabelaGpu> getFkComputadorG() {
        return fkComputadorG;
    }

    public void setFkComputadorG(Set<TabelaGpu> fkComputadorG) {
        this.fkComputadorG = fkComputadorG;
    }

  }
