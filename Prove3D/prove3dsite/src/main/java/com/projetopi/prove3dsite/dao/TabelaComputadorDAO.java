
package com.projetopi.prove3dsite.dao;

import com.projetopi.prove3dsite.tabelas.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

@Repository
public interface TabelaComputadorDAO extends JpaRepository<TabelaComputador, Long>{

    @Query(value = "select cpu.id_cpu, cpu.utilizacao, cpu.temperatura, cpu.dt_Hora from tbComputador c join " +
            "tbCpu cpu where c.id_Computador = cpu.fk_ComputadorCPU_id_computador " +
            "and cpu.fk_ComputadorCPU_id_computador = ?1 order by cpu.dt_Hora desc limit 10;", nativeQuery = true)
    Object[] filtraCPU(TabelaComputador fkUser);

    @Query(value = "select d.id_disco, d.v_Gravacao, d.v_Leitura, d.dt_Hora from tbComputador c join tbDisco d " +
            "where c.id_Computador = d.fk_ComputadorD_id_Computador " +
            "and d.fk_ComputadorD_id_Computador = ?1 order by d.dt_Hora desc limit 10;", nativeQuery = true)
    Object[] filtraDisco(TabelaComputador fkComputador);

    @Query(value = "select m.id_memoria, m.em_Uso, m.dt_Hora from tbComputador c join tbMemoria m " +
            "where c.id_Computador = m.fk_ComputadorM_id_Computador " +
            "and m.fk_ComputadorM_id_Computador = ?1 order by m.dt_Hora desc limit 10;", nativeQuery = true)
    Object[] filtraMemoria(TabelaComputador fkComputador);

    @Query("Select c.idComputador, c.sistemaOperacional, c.nmComputador, c.modelo, c.fkUsuario from TabelaComputador c join c.fkUsuario u where c.fkUsuario = u.idUsuario and u.idUsuario = ?1")
    Object[] findData(Long id);

}
