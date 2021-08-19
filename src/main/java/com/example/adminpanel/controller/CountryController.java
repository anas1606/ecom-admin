package com.example.adminpanel.controller;

import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("add/{name}")
    public ResponseModel addCountry(@PathVariable("name") String name) {
        return countryService.addCountry(name);
    }
}
