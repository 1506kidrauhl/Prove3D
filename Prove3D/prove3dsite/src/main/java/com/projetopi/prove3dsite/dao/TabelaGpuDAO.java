
package com.projetopi.prove3dsite.dao;


import com.projetopi.prove3dsite.tabelas.TabelaGpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TabelaGpuDAO extends JpaRepository<TabelaGpu, Long>{

    @Query("select g from TabelaGpu g join g.fkUsuarioG u where g.fkComputadorG = u.idUsuario " +
            "and g.fkUsuarioG = ?1 and g.dtHora >= ?2")
    List<TabelaGpu> filtraGPU(Long fkComputador, Date dtHora);

}
