package com.example.adminpanel.model.customer;

import com.example.commanentity.Customer;
import com.example.commanentity.CustomerAddress;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDetailDTO {
    private String id;
    private String emailid;
    private String name;
    private String phoneno;
    private int gender;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private String pincode;
    private int status;
    private boolean emailVerified;
    private List<String> hobby;

    public CustomerDetailDTO(Customer customer, CustomerAddress customerAddress) {
        this.id = customer.getId();
        this.emailid = customer.getEmailid();
        this.gender = customer.getGender();
        this.phoneno = customer.getPhoneno();
        this.name = customer.getFirst_name() + " " + customer.getLast_name();
        this.status = customer.getStatus();
        this.address1 = customerAddress.getAddress1();
        this.address2 = customerAddress.getAddress2();
        this.pincode = customerAddress.getPincode();
        this.country = customerAddress.getCountry().getName();
        this.state = customerAddress.getState().getName();
        this.emailVerified = customer.isEmailverified();
    }
}
