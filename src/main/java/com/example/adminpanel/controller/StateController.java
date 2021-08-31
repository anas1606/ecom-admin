package com.example.adminpanel.controller;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.model.state.NewStateModel;
import com.example.adminpanel.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping("")
    public ResponseModel addState(@RequestBody NewStateModel model) {
        return stateService.addState(model);
    }

    @GetMapping("")
    public ResponseModel getCountryList() {
        return stateService.stateList();
    }

    @PutMapping("")
    public ResponseModel updateStatus(@RequestBody ActiveInactiveModel model) {
        return stateService.update(model);
    }

}
