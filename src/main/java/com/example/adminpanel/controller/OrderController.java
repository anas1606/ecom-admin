package com.example.adminpanel.controller;

import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("list")
    public PageResponseModel list(@RequestBody PageDetailModel model) {
        return orderService.list(model);
    }

    @GetMapping("view/{id}")
    public ResponseModel view(@PathVariable("id") String id) {
        return orderService.view(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseModel delete(@PathVariable("id") String id) {
        return orderService.delete(id);
    }
}
