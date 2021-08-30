package com.example.adminpanel.model.vendor;

import com.example.commanentity.CompanyAddress;
import com.example.commanentity.Vendor;
import lombok.Data;

@Data
public class VendorDetailDTO {
    private String id;
    private String emailid;
    private String name;
    private String phoneno;
    private String companyName;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private String pincode;
    private int status;

    public VendorDetailDTO(Vendor vendor, CompanyAddress companyAddress) {
        this.id = vendor.getId();
        this.emailid = vendor.getEmailid();
        this.phoneno = vendor.getPhoneno();
        this.name = vendor.getFirst_name() + " " + vendor.getLast_name();
        this.companyName = vendor.getCompany_name();
        this.status = vendor.getStatus();
        this.address1 = companyAddress.getAddress1();
        this.address2 = companyAddress.getAddress2();
        this.pincode = companyAddress.getPincode();
        this.country = companyAddress.getCountry().getName();
        this.state = companyAddress.getState().getName();
    }
}
