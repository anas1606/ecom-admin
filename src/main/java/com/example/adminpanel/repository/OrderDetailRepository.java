package com.example.adminpanel.repository;

import com.example.adminpanel.model.order.OrderDTO;
import com.example.adminpanel.model.order.OrderDetailDTO;
import com.example.commanentity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    @Query("SELECT new com.example.adminpanel.model.order.OrderDTO(o,ca.pincode) " +
            "FROM OrderDetail o " +
            "INNER JOIN CustomerAddress ca ON o.customer.id = ca.customer.id ")
    Page<OrderDTO> findALL(Pageable page);

    @Query("SELECT new com.example.adminpanel.model.order.OrderDTO(o,ca.pincode) FROM OrderDetail o " +
            "INNER JOIN CustomerAddress ca on o.customer.id = ca.customer.id " +
            "WHERE lower(o.id) like lower(:search) OR lower(ca.pincode) like lower(:search) ")
    Page<OrderDTO> findAllBySearch(String search, Pageable page);

    @Query("SELECT new com.example.adminpanel.model.order.OrderDetailDTO(o,ca)" +
            " FROM OrderDetail o LEFT JOIN CustomerAddress ca ON o.customer.id = ca.customer.id" +
            " WHERE o.id = :id")
    OrderDetailDTO findAllByid(String id);

    int countById(String id);

    void deleteByCustomer_Id(String id);
}
