
package com.projetopi.prove3dsite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetopi.prove3dsite.tabelas.TabelaCpu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaCpuDAO extends JpaRepository<TabelaCpu, Long> {

}
