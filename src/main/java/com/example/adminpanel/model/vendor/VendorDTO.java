package com.example.adminpanel.model.vendor;

import com.example.commanentity.Vendor;
import lombok.Data;

@Data
public class VendorDTO {
    private String id;
    private String name;
    private String companyName;
    private String country;
    private String state;
    private int status;
    private String createdAt;

    public VendorDTO(Vendor vendor, String country, String state) {
        this.id = vendor.getId();
        this.name = vendor.getFirst_name() + " " + vendor.getLast_name();
        this.companyName = vendor.getCompany_name();
        this.status = vendor.getStatus();
        this.country = country;
        this.state = state;
        this.createdAt = vendor.getCreated_at().toInstant().toString();
    }
}
