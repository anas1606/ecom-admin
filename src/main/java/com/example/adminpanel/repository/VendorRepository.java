package com.example.adminpanel.repository;

import com.example.adminpanel.model.customer.CustomerDTO;
import com.example.adminpanel.model.customer.CustomerDetailDTO;
import com.example.adminpanel.model.vendor.VendorDTO;
import com.example.adminpanel.model.vendor.VendorDetailDTO;
import com.example.commanentity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, String> {

    @Query("SELECT new com.example.adminpanel.model.vendor.VendorDTO(v,ca.country.name,ca.state.name) " +
            "FROM Vendor v " +
            "LEFT JOIN Company_Address ca on v.id = ca.vendor.id")
    Page<VendorDTO> findALL(Pageable page);

    @Query("SELECT new com.example.adminpanel.model.vendor.VendorDTO(v,ca.country.name,ca.state.name) " +
            "FROM Vendor v " +
            "LEFT JOIN Company_Address ca on v.id = ca.vendor.id " +
            "WHERE lower(v.first_name) like lower(:search) OR lower(v.last_name) like lower(:search) " +
            "OR lower(ca.country.name) like lower(:search) OR lower(ca.state.name) like lower(:search) ")
    Page<VendorDTO> findAllBySearch(String search, Pageable page);

    @Query("SELECT new com.example.adminpanel.model.vendor.VendorDetailDTO(v,ca)" +
            " FROM Vendor v LEFT JOIN Company_Address ca ON v.id = ca.vendor.id" +
            " WHERE v.id = :id")
    VendorDetailDTO findAllByid(String id);

    int countById(String id);
}
