
package com.projetopi.prove3dsite.dao;

import com.projetopi.prove3dsite.tabelas.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TabelaComputadorDAO extends JpaRepository<TabelaComputador, Long>{


    @Query("select cpu.voltagem, cpu.temperatura, cpu.dtHora from TabelaComputador c join " +
            "c.fkComputadorCPU cpu where c.idComputador = cpu.fkComputadorCPU " +
            "and cpu.fkComputadorCPU = ?1")
    Object[] filtraCPU(TabelaComputador fkUser);

    @Query("select d.vGravacao, d.vLeitura, d.dtHora from TabelaComputador c join c.fkComputadorD d " +
            "where c.fkComputadorD = d.fkComputadorD " +
            "and d.fkComputadorD = ?1")
    Object[] filtraDisco(TabelaComputador fkComputador);

    @Query("select m.emUso, m.dtHora from TabelaComputador c join c.fkComputadorM m " +
            "where c.fkComputadorM = m.fkComputadorM " +
            "and m.fkComputadorM = ?1")
    Object[] filtraMemoria(TabelaComputador fkComputador);

    @Query("Select c.idComputador, c.sistemaOperacional, c.nmComputador, c.modelo, c.fkUsuario from TabelaComputador c join c.fkUsuario u where c.fkUsuario = u.idUsuario and u.idUsuario = ?1")
    Object[] findData(Long id);




}
