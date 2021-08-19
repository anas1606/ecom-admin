package com.example.adminpanel.repository;

import com.example.commanentity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    int countByName (String name);
}
