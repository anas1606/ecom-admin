package com.example.adminpanel.controller;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/{name}")
    public ResponseModel addCountry(@PathVariable("name") String name) {
        return countryService.addCountry(name);
    }

    @GetMapping("")
    public ResponseModel getCountryList() {

        return countryService.countrylist();
    }

    @PutMapping("")
    public ResponseModel updateStatus(@RequestBody ActiveInactiveModel model) {
        return countryService.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseModel delete(@PathVariable("id") String id) {
        return countryService.delete(id);
    }
}
