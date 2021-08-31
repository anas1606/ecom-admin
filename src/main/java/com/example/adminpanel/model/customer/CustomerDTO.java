package com.example.adminpanel.model.customer;

import com.example.commanentity.Customer;
import lombok.Data;

@Data
public class CustomerDTO {
    private String id;
    private String emailid;
    private String name;
    private String phoneno;
    private int gender;
    private String country;
    private String state;
    private int status;
    private String createdAt;

    public CustomerDTO(Customer customer, String country, String state) {
        this.id = customer.getId();
        this.emailid = customer.getEmailid();
        this.gender = customer.getGender();
        this.phoneno = customer.getPhoneno();
        this.name = customer.getFirst_name() + " " + customer.getLast_name();
        this.country = country;
        this.state = state;
        this.status = customer.getStatus();
        this.createdAt = customer.getCreated_at().toInstant().toString();
    }
}
