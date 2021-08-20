package com.example.adminpanel.controller;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/list")
    public PageResponseModel listing(@RequestBody PageDetailModel model) {
        return customerService.coustomerlist(model);
    }

    @PutMapping("")
    public ResponseModel updateStatus(@RequestBody ActiveInactiveModel model) {
        return customerService.updateStatus(model);
    }

    @DeleteMapping("/{id}")
    public ResponseModel delete(@PathVariable("id") String id) {
        return customerService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseModel viewCustomer(@PathVariable("id") String id) {
        return customerService.viewCustomer(id);
    }
}
