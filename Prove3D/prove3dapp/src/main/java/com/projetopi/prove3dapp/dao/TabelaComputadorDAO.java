
package com.projetopi.prove3dapp.dao;

import com.projetopi.prove3dapp.tabelas.TabelaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projetopi.prove3dapp.tabelas.TabelaComputador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabelaComputadorDAO extends JpaRepository<TabelaComputador, Long>{

    @Query("Select c from TabelaComputador c where c.fkUsuario = ?1")
    TabelaComputador findData(TabelaUsuario idUser);

}
