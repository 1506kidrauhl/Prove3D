
package com.projetopi.prove3dsite.dao;


import com.projetopi.prove3dsite.tabelas.TabelaLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TabelaLogDAO extends JpaRepository<TabelaLog, Long>{

    @Query("Select l.componente, l.descricao, l.tipo ,l.dtHora from TabelaLog l join l.fkUsuario u where l.fkUsuario = u.idUsuario " +
            "and l.dtHora >= ?1 and l.dtHora <= ?2 and " +
            "l.componente = ?3 and u.idUsuario = ?4")
    Object[] findAllByInitialAndFinal(Date inicial, Date fim, String componente, Long id);

    @Query("Select l.componente, l.descricao, l.tipo ,l.dtHora from TabelaLog l join l.fkUsuario u where l.fkUsuario = u.idUsuario and " +
            "l.dtHora >= ?1 and l.dtHora <= ?2 and "
            + "l.tipo = ?3 and l.componente = ?4 and u.idUsuario = ?5")
    Object[] findByComponente(Date inicial, Date fim, String tipo, String comp, Long id);

}
