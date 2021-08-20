package com.example.adminpanel.repository;

import com.example.commanentity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    int countByName(String name);

    @Query("SELECT c.name FROM Category c WHERE c.status = 1")
    List<String> findAllByStatus ();
}
