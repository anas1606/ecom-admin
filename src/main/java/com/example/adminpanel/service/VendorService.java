package com.example.adminpanel.service;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.ResponseModel;

public interface VendorService {
    PageResponseModel vendorlist(PageDetailModel model);

    ResponseModel updateStatus(ActiveInactiveModel model);

    PageResponseModel productList (PageDetailModel model);

    ResponseModel updateProduct (ActiveInactiveModel model);

    ResponseModel viewVendor(String id);
}
