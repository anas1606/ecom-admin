package com.example.adminpanel.service;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.model.state.NewStateModel;

public interface StateService {
    ResponseModel addState(NewStateModel model);

    ResponseModel stateList();

    ResponseModel update(ActiveInactiveModel model);

    ResponseModel delete(String id);
}
