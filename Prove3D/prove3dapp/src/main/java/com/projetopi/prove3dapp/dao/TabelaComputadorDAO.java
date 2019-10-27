
package com.projetopi.prove3dapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaComputadorDAO extends JpaRepository<TabelaComputador, Long>{
    
}
