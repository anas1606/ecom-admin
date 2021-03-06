package com.example.adminpanel.repository;

import com.example.adminpanel.model.country.CountryDTO;
import com.example.commanentity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    int countByName(String name);

    @Query("SELECT c.name FROM Country c WHERE c.status = 1 ORDER BY c.name ASC")
    List<String> findAllByStatus();

    @Query("SELECT new com.example.adminpanel.model.country.CountryDTO(c) FROM Country c ORDER BY c.name ASC")
    List<CountryDTO> findByALL();

    int countById(String id);
}
