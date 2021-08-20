package com.example.adminpanel.repository;

import com.example.commanentity.Customer_Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerHobbyRepository extends JpaRepository<Customer_Hobby, String> {

    @Query("SELECT concat(h.hobby.name,',') FROM Customer_Hobby h WHERE h.customer.id = :id")
    String findByid(String id);
}
