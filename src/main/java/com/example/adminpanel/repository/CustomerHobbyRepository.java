package com.example.adminpanel.repository;

import com.example.commanentity.CustomerHobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerHobbyRepository extends JpaRepository<CustomerHobby, String> {

    @Query("SELECT h.hobby.name FROM CustomerHobby h WHERE h.customer.id = :id")
    List<String> findByid(String id);

    void deleteByCustomer_Id (String id);
}
