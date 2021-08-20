package com.example.adminpanel.controller;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hobby")
public class HobbyController {

    @Autowired
    private HobbyService hobbyService;

    @PostMapping("/{name}")
    public ResponseModel addHobby(@PathVariable("name") String name) {
        return hobbyService.addHobby(name);
    }

    @GetMapping("")
    public ResponseModel list() {
        return hobbyService.hobbyList();
    }

    @PutMapping("")
    public ResponseModel updateStatus(@RequestBody ActiveInactiveModel model) {
        return hobbyService.update(model);
    }
}
