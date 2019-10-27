
package com.projetopi.prove3dsite.dao;

import com.projetopi.prove3dsite.tabelas.TabelaProcessos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaProcessosDAO extends JpaRepository<TabelaProcessos, Long>{
    
}