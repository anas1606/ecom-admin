package com.example.adminpanel.repository;

import com.example.adminpanel.model.state.StateDTO;
import com.example.commanentity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, String> {
    int countByNameAndCountry_Id(String name, String country);

    @Query("SELECT s.name FROM State s WHERE s.country.name = :country AND s.status = 1")
    List<String> findAllByCountry(String country);

    @Query("SELECT new com.example.adminpanel.model.state.StateDTO(s.id,s.name,s.status,s.country.name,s.updated_by) FROM State s ORDER BY s.country.name ASC")
    List<StateDTO> findByALL();
}
