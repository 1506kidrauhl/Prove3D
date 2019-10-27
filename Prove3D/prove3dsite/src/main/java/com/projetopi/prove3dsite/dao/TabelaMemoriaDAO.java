
package com.projetopi.prove3dsite.dao;


import com.projetopi.prove3dsite.tabelas.TabelaMemoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaMemoriaDAO extends JpaRepository<TabelaMemoria, Long>{

}
