package com.example.adminpanel.model.country;

import com.example.commanentity.Country;
import lombok.Data;

@Data
public class CountryDropDownDTO {
    private String id;
    private String name;

    public CountryDropDownDTO(Country country){
        this.id = country.getId();
        this.name = country.getName();
    }
}
