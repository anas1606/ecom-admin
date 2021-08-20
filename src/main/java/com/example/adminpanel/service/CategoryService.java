package com.example.adminpanel.service;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;

public interface CategoryService {

    ResponseModel addCategory(String name);

    ResponseModel listCategoryName();

    ResponseModel list();

    ResponseModel update(ActiveInactiveModel model);
}
