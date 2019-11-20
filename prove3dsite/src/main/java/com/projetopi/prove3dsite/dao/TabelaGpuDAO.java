
package com.projetopi.prove3dsite.dao;


import com.projetopi.prove3dsite.tabelas.TabelaGpu;
import com.projetopi.prove3dsite.tabelas.TabelaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TabelaGpuDAO extends JpaRepository<TabelaGpu, Long>{

    @Query(value = "select top 10 g.id_Gpu, g.utilizacao, g.memoria, g.dt_Hora from tbGpu g join tbUsuario u " +
            "where u.id_usuario = g.fk_usuariog_id_usuario " +
            "and g.fk_usuariog_id_usuario = ?1 order by g.dt_Hora desc;" , nativeQuery = true)
    Object[] filtraGPU(TabelaUsuario fkUser);
}
