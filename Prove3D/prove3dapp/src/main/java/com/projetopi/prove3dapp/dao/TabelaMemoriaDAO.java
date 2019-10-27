package com.projetopi.prove3dapp.dao;

import com.projetopi.prove3dapp.tabelas.TabelaMemoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaMemoriaDAO extends JpaRepository<TabelaMemoria, Long> {

}
