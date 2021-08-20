package com.example.adminpanel.controller;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping("/{name}")
    public ResponseModel addState(@PathVariable("name") String name) {
        return stateService.addState(name);
    }

    @GetMapping("")
    public ResponseModel getCountryList() {
        return stateService.stateList();
    }

    @PutMapping("")
    public ResponseModel updateStatus(@RequestBody ActiveInactiveModel model) {
        return stateService.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseModel delete(@PathVariable("id") String id) {
        return stateService.delete(id);
    }
}
