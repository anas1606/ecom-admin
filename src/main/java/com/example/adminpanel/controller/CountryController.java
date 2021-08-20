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

    @PostMapping("add/{name}")
    public ResponseModel addCountry(@PathVariable("name") String name) {
        return countryService.addCountry(name);
    }

    @GetMapping("name/list")
    public ResponseModel getCountryNameList() {
        return countryService.countrynamelist();
    }

    @GetMapping("list")
    public ResponseModel getCountryList() {
        return countryService.countrylist();
    }

    @PutMapping("update/status")
    public ResponseModel updateStatus(@RequestBody ActiveInactiveModel model) {
        return countryService.updateStatus(model);
    }

    @DeleteMapping("delete/{id}")
    public ResponseModel delete(@PathVariable("id") String id) {
        return countryService.delete(id);
    }
}
