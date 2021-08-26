package com.example.adminpanel.controller;

import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.DropdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dropdown/")
public class DropDownController {

    @Autowired
    private DropdownService dropdownService;

    @GetMapping("country")
    public ResponseModel country(){
        return dropdownService.country();
    }

    @GetMapping("state/{name}")
    public ResponseModel state(@PathVariable("name") String name){
        return dropdownService.state(name);
    }

    @GetMapping("hobby")
    public ResponseModel hobby(){
        return dropdownService.hobby();
    }

    @GetMapping("category")
    public ResponseModel category(){
        return dropdownService.category();
    }
}
