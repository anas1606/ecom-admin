package com.example.adminpanel.repository;

import com.example.adminpanel.model.product.ProductDTO;
import com.example.commanentity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT new com.example.adminpanel.model.product.ProductDTO(p)" +
            " FROM Product p WHERE p.vendor.id = :id AND p.name like :search")
    Page<ProductDTO> findByVendor_Id(String id, String search, Pageable page);

    @Query("SELECT new com.example.adminpanel.model.product.ProductDTO(p)" +
            " FROM Product p WHERE p.vendor.id = :id AND p.name like :search AND p.status = :status")
    Page<ProductDTO> findByVendor_IdAndStatus(String id, String search,int status, Pageable page);
}
