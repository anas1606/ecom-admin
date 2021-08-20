package com.example.adminpanel.model.order;

import com.example.commanentity.Order_Detail;
import lombok.Data;

@Data
public class OrderDTO {
    private String id;
    private String name;
    private String productName;
    private String price;
    private int status;
    private int pincode;

    public OrderDTO(Order_Detail orderDetail,int pincode) {
        this.id = orderDetail.getId();
        this.price = orderDetail.getProduct().getPrice().toString();
        this.productName = orderDetail.getProduct().getName();
        this.name = orderDetail.getCustomer().getFirst_name() + " " + orderDetail.getCustomer().getLast_name();
        this.status = orderDetail.getStatus();
        this.pincode = pincode;
    }
}
