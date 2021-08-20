package com.example.adminpanel.controller;

import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hobby/")
public class HobbyController {

    @Autowired
    private HobbyService hobbyService;

    @PostMapping("add/{name}")
    public ResponseModel addHobby(@PathVariable("name") String name) {
        return hobbyService.addHobby(name);
    }

    @GetMapping("name/list")
    public ResponseModel namelist() {
        return hobbyService.listHobbyNames();
    }
}
