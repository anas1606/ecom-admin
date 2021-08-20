package com.example.adminpanel.model.order;

import com.example.commanentity.Customer_Address;
import com.example.commanentity.Order_Detail;
import lombok.Data;

@Data
public class OrderDetailDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String emailid;
    private String phoneno;
    private String productName;
    private String price;
    private String category;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private int pincode;

    public OrderDetailDTO(Order_Detail orderDetail, Customer_Address customerAddress) {
        this.id = orderDetail.getId();
        this.firstName = orderDetail.getCustomer().getFirst_name();
        this.lastName = orderDetail.getCustomer().getLast_name();
        this.emailid = orderDetail.getCustomer().getEmailid();
        this.phoneno = orderDetail.getCustomer().getPhoneno();
        this.productName = orderDetail.getProduct().getName();
        this.price = orderDetail.getProduct().getPrice().toString();
        this.category = orderDetail.getProduct().getCategory().getName();
        this.address1 = customerAddress.getAddress1();
        this.address2 = customerAddress.getAddress2();
        this.country = customerAddress.getCountry().getName();
        this.state = customerAddress.getState().getName();
        this.pincode = customerAddress.getPincode();
    }
}
