package com.example.adminpanel.service;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.ResponseModel;

public interface CustomerService {
    PageResponseModel coustomerlist(PageDetailModel model);

    ResponseModel updateStatus(ActiveInactiveModel model);

    ResponseModel delete(String id);

    ResponseModel viewCustomer(String id);
}
