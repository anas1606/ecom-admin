package com.example.adminpanel.service;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;

public interface CountryService {
    ResponseModel addCountry(String name);

    ResponseModel countrynamelist();

    ResponseModel countrylist();

    ResponseModel update(ActiveInactiveModel model);

    ResponseModel delete(String id);
}
