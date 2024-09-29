package com.mayoreo.cojutepeque.domain.repository;

import com.mayoreo.cojutepeque.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Users, Long> {
    @Query("select u from Users u where username = ?1 and pass = ?2")
    Users authenticate(String username, String password);

    @Query(value = "select * from users where username = ?1", nativeQuery = true)
    Users findByUserName(String username);

    @Query(value = "select * from users where BINARY iduser = ?1 and pass = ?2", nativeQuery = true)
    Users changePass(long id, String pass);
}
