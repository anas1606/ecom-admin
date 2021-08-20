package com.example.adminpanel.service;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;

public interface HobbyService {
    ResponseModel addHobby(String name);

    ResponseModel listHobbyNames();

    ResponseModel hobbyList();

    ResponseModel updateStatus(ActiveInactiveModel model);
}
