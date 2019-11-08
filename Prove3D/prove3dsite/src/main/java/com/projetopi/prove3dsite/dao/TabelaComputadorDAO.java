
package com.projetopi.prove3dsite.dao;

import com.projetopi.prove3dsite.tabelas.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TabelaComputadorDAO extends JpaRepository<TabelaComputador, Long>{

    @Query("select cpu from TabelaComputador c join c.fkComputadorCPU cpu where c.fkComputadorCPU = cpu.idCpu " +
            "and c.fkUsuario = ?1 and cpu.dtHora >= ?2")
    List<TabelaCpu> filtraCPU(Long fkComputador, Date dtHora);

    @Query("select d from TabelaComputador c join c.fkComputadorD d where c.fkComputadorD = d.idDisco " +
            "and c.fkUsuario = ?1 and d.dtHora >= ?2")
    List<TabelaDisco> filtraDisco(Long fkComputador, Date dtHora);

    @Query("select m from TabelaComputador c join c.fkComputadorM m where c.fkComputadorM = m.idMemoria " +
            "and c.fkUsuario = ?1 and m.dtHora >= ?2")
    List<TabelaMemoria> filtraMemoria(Long fkComputador, Date dtHora);


}
