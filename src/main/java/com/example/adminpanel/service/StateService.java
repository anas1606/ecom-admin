package com.example.adminpanel.service;

import com.example.adminpanel.model.ResponseModel;

public interface StateService {
    ResponseModel addState(String name);

    ResponseModel statenamelist();

    ResponseModel stateList();
}
