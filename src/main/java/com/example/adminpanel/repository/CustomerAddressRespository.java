package com.example.adminpanel.repository;

import com.example.commanentity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRespository extends JpaRepository<CustomerAddress,String> {

    void deleteByCustomer_Id (String id);
}
