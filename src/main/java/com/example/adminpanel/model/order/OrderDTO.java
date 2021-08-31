package com.example.adminpanel.model.order;

import com.example.commanentity.OrderDetail;
import lombok.Data;

@Data
public class OrderDTO {
    private String id;
    private String name;
    private String productName;
    private String price;
    private int status;
    private String pincode;
    private String createdAt;
    private String vendor;

    public OrderDTO(OrderDetail orderDetail,String pincode) {
        this.id = orderDetail.getId();
        this.price = orderDetail.getProduct().getPrice().toString();
        this.productName = orderDetail.getProduct().getName();
        this.name = orderDetail.getCustomer().getFirst_name() + " " + orderDetail.getCustomer().getLast_name();
        this.status = orderDetail.getStatus();
        this.pincode = pincode;
        this.createdAt = orderDetail.getCreated_at().toInstant().toString();
        this.vendor = orderDetail.getProduct().getVendor().getCompany_name();
    }
}
