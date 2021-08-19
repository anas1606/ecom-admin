package com.example.adminpanel.repository;

import com.example.adminpanel.model.country.CountryDTO;
import com.example.adminpanel.model.state.StateDTO;
import com.example.commanentity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, String> {
    int countByName(String name);

    @Query("SELECT s.name FROM State s WHERE s.status = 1")
    List<String> findAllByStatus();

    @Query("SELECT new com.example.adminpanel.model.state.StateDTO(s) FROM State s")
    List<StateDTO> findByALL();
}
