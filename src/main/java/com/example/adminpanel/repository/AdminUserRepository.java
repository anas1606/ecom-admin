package com.example.adminpanel.repository;

import com.example.commanentity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserRepository extends JpaRepository<Admin, String> {

    @Query("SELECT count(a.adminuid) FROM Admin a WHERE a.sessiontoken =:token")
    int countBySessionTokenAndStatus(String token);

    List<Admin> findByEmailid(String emailID);
}
