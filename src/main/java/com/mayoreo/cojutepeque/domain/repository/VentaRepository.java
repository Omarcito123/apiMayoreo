package com.mayoreo.cojutepeque.domain.repository;

import com.mayoreo.cojutepeque.domain.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Ventas, Long> {
    @Query(value = "select * from ventas where DATE(dateadd) = :fecha order by idventa desc" , nativeQuery = true)
    List<Ventas> getVentasByDate(@Param("fecha") String fecha);

    @Query(value = "SELECT SUM(totalsaleprice) AS preciototal, SUM(ganancia) AS ganancia, dateadd " +
            "FROM ventas " +
            "WHERE MONTH(TIMESTAMP(dateadd)) = :mes AND YEAR(TIMESTAMP(dateadd)) = :anio " +
            "GROUP BY DAY(TIMESTAMP(dateadd)) ORDER BY idventa DESC;" , nativeQuery = true)
    List<Object[]> getVentasMensuales(@Param("mes") String mes, @Param("anio") String anio);
}
