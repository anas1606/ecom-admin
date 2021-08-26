package com.example.adminpanel.service;

import com.example.adminpanel.model.ResponseModel;

public interface DropdownService {

    ResponseModel country ();

    ResponseModel state (String name);

    ResponseModel hobby ();

    ResponseModel category ();
}
