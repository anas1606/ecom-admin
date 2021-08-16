package com.example.adminpanel.repository;

import com.example.commanentity.Admin;
import com.example.commanentity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserRepository extends JpaRepository<Admin, String> {

    @Query("SELECT count(a.adminUID) FROM Admin a WHERE a.sessionToken =:token AND a.status ='ACTIVE' ")
    int countBySessionTokenAndStatus(String token);

    List<Admin> findByEmailID(String emailID);

    Admin findByEmailIDAndStatus(String emailId, Status status);
}
