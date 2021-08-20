package com.example.adminpanel.service;

import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.ResponseModel;

public interface OrderService {
    PageResponseModel list(PageDetailModel model);

    ResponseModel view(String id);

    ResponseModel delete(String id);
}
