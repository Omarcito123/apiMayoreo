package com.mayoreo.cojutepeque.domain.repository;

import com.mayoreo.cojutepeque.domain.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Products, Long> {

    @Query(value = "select * from products where productname = :name order by idproduct desc LIMIT 1" , nativeQuery = true)
    Products findProductByName(@Param("name") String name);
}
