package com.example.adminpanel.controller;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("/list")
    public PageResponseModel list(@RequestBody PageDetailModel model) {
        return vendorService.vendorlist(model);
    }

    @PutMapping("")
    public ResponseModel updateStatus(@RequestBody ActiveInactiveModel model) {
        return vendorService.updateStatus(model);
    }

    @PutMapping("/product")
    public ResponseModel updateProductStatus(@RequestBody ActiveInactiveModel model) {
        return vendorService.updateProduct(model);
    }

    @PostMapping("/product/list")
    public PageResponseModel getProducts (@RequestBody PageDetailModel model){
        return vendorService.productList(model);
    }

    @GetMapping("/{id}")
    public ResponseModel view(@PathVariable("id") String id) {
        return vendorService.viewVendor(id);
    }
}
