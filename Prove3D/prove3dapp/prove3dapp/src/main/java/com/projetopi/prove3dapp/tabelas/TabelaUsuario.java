package com.projetopi.prove3dapp.tabelas;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TBUSUARIO")
public class TabelaUsuario {

    @Id
    @GeneratedValue
    private Long idUsuario;

    @Column(length = 120)
    private String nome;

    @Column(length = 20)
    private String login;

    @Column(length = 14)
    private String senha;

    @Column(length = 100)
    private String email;

    @Column(length = 14)
    private String telefone;

    @Column(length = 14)
    private String cpf;

    
    @OneToMany(mappedBy = "fkUsuario")
    private Set<TabelaComputador> fkUsuario  = new HashSet<>();
    
    @OneToMany (mappedBy="fkUsuario")
    private Set<TabelaLog> fkUsuarioL= new HashSet<>();

    public Set<TabelaLog> getFkUsuarioL() {
        return fkUsuarioL;
    }

    public void setFkUsuarioL(Set<TabelaLog> fkUsuarioL) {
        this.fkUsuarioL = fkUsuarioL;
    }
    
    

    public Set<TabelaComputador> getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Set<TabelaComputador> fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
    

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
