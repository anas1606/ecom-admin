package com.example.adminpanel.controller;

import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/state/")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping("add/{name}")
    public ResponseModel addState(@PathVariable("name") String name) {
        return stateService.addState(name);
    }
}
