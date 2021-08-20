package com.example.adminpanel.repository;

import com.example.adminpanel.model.customer.CustomerDTO;
import com.example.adminpanel.model.customer.CustomerDetailDTO;
import com.example.commanentity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("SELECT new com.example.adminpanel.model.customer.CustomerDTO(c,ca.country.name,ca.state.name) FROM Customer c " +
            "LEFT JOIN Customer_Address ca on c.id = ca.customer.id")
    Page<CustomerDTO> findALL(Pageable page);

    @Query("SELECT new com.example.adminpanel.model.customer.CustomerDTO(c,ca.country.name,ca.state.name) FROM Customer c " +
            "LEFT JOIN Customer_Address ca on c.id = ca.customer.id " +
            "WHERE lower(c.first_name) like lower(:search) OR lower(c.last_name) like lower(:search) " +
            "OR lower(ca.country.name) like lower(:search) OR lower(ca.state.name) like lower(:search) ")
    Page<CustomerDTO> findAllBySearch(String search, Pageable page);

    @Query("SELECT new com.example.adminpanel.model.customer.CustomerDetailDTO(c,ca)" +
            " FROM Customer c LEFT JOIN Customer_Address ca ON c.id = ca.customer.id" +
            " WHERE c.id = :id")
    CustomerDetailDTO findAllByid(String id);

    int countById(String id);

}
