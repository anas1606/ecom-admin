package com.example.adminpanel.service;

import com.example.adminpanel.model.ResponseModel;

public interface CategoryService {

    ResponseModel addCategory (String name);

    ResponseModel listCategoryName ();
}
