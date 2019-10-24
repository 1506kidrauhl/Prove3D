package com.projetopi.prove3dapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetopi.prove3dapp.tabelas.TabelaUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaUsuarioDAO extends JpaRepository<TabelaUsuario, Long>{
 
    //Estamos criando uma query para o DAO da tabela de usuário.
    //Os números representam o que o usuário digitou, em suas respectivas ordens,
    // O u é equivalente ao * 
    @Query("Select u from TabelaUsuario u where u.login = ?1 and u.senha = ?2")
            
    //Ao chamar o método findById, é necessário passar dois parâmetros
    TabelaUsuario findByLogin(String login, String senha);
    
}
