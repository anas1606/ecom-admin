package com.example.adminpanel.model.country;

import com.example.commanentity.Country;
import lombok.Data;

@Data
public class CountryDTO {
    private String id;
    private String name;
    private int status;
    private String updatedBy;

    public CountryDTO (Country country){
        this.id = country.getId();
        this.name = country.getName();
        this.status = country.getStatus();
        this.updatedBy = country.getUpdated_by();
    }
}
