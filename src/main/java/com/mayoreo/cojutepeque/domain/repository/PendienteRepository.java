package com.mayoreo.cojutepeque.domain.repository;

import com.mayoreo.cojutepeque.domain.entity.Pendientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PendienteRepository extends JpaRepository<Pendientes, Long> {

    @Query(value = "SELECT SUM(totalsaleprice) AS preciototal, SUM(ganancia) AS ganancia, nombredeudor, dateadd " +
            "FROM pendientes " +
            "GROUP BY nombredeudor ORDER BY idpendiente DESC;" , nativeQuery = true)
    List<Object[]> getPendientesList();

    @Query(value = "SELECT * FROM pendientes WHERE nombredeudor = :nombre AND dateadd = :fecha" , nativeQuery = true)
    List<Pendientes> findList(@Param("nombre") String nombre, @Param("fecha") String fecha);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM pendientes WHERE nombredeudor = :nombre AND dateadd = :fecha" , nativeQuery = true)
    int deletePendientesList(@Param("nombre") String nombre, @Param("fecha") String fecha);
}
