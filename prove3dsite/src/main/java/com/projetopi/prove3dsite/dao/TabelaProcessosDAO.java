
package com.projetopi.prove3dsite.dao;

import com.projetopi.prove3dsite.tabelas.TabelaProcessos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaProcessosDAO extends JpaRepository<TabelaProcessos, Long>{

    @Query(value = "SELECT TOP 10 p.pid, p.processo, p.uso_Cpu, p.uso_memoria, p.tempo_atividade" +
            " FROM tbProcessos p WHERE p.fk_computadorp_id_computador = ?1 AND " +
            "p.fk_usuariop_id_usuario = ?2 ORDER BY p.data_hora DESC", nativeQuery = true)
    Object[] findMaxUsageCpuPerProcess(Long idPc, Long idUser);

}