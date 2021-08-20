package com.example.adminpanel.controller;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/vendor/")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("list")
    public PageResponseModel list(@RequestBody PageDetailModel model) {
        return vendorService.vendorlist(model);
    }

    @PutMapping("update/status")
    public ResponseModel updateStatus(@RequestBody ActiveInactiveModel model) {
        return vendorService.updateStatus(model);
    }

    @DeleteMapping("delete/{id}")
    public ResponseModel delete(@PathVariable("id") String id) {
        return vendorService.delete(id);
    }

    @GetMapping("view/{id}")
    public ResponseModel view(@PathVariable("id") String id) {
        return vendorService.viewVendor(id);
    }
}
