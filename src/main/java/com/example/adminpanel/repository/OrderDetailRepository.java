package com.example.adminpanel.repository;

import com.example.adminpanel.model.customer.CustomerDetailDTO;
import com.example.adminpanel.model.order.OrderDTO;
import com.example.adminpanel.model.order.OrderDetailDTO;
import com.example.commanentity.Order_Detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<Order_Detail, String> {

    @Query("SELECT new com.example.adminpanel.model.order.OrderDTO(o,ca.pincode) " +
            "FROM Order_Detail o " +
            "INNER JOIN Customer_Address ca ON o.customer.id = ca.customer.id ")
    Page<OrderDTO> findALL(Pageable page);

    @Query("SELECT new com.example.adminpanel.model.order.OrderDTO(o,ca.pincode) FROM Order_Detail o " +
            "INNER JOIN Customer_Address ca on o.customer.id = ca.customer.id " +
            "WHERE lower(o.id) like lower(:search) OR lower(ca.pincode) like lower(:search) ")
    Page<OrderDTO> findAllBySearch(String search, Pageable page);

    @Query("SELECT new com.example.adminpanel.model.order.OrderDetailDTO(o,ca)" +
            " FROM Order_Detail o LEFT JOIN Customer_Address ca ON o.customer.id = ca.customer.id" +
            " WHERE o.id = :id")
    OrderDetailDTO findAllByid(String id);

    int countById(String id);
}
