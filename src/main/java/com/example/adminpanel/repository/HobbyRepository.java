package com.example.adminpanel.repository;

import com.example.commanentity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, String> {
    int countByName(String name);

    @Query("SELECT h.name FROM Hobby h WHERE h.status = 1")
    List<String> findAllName();
}
