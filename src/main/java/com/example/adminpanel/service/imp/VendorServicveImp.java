package com.example.adminpanel.service.imp;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.model.customer.CustomerDetailDTO;
import com.example.adminpanel.model.vendor.VendorDTO;
import com.example.adminpanel.model.vendor.VendorDetailDTO;
import com.example.adminpanel.repository.VendorRepository;
import com.example.adminpanel.service.VendorService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class VendorServicveImp implements VendorService {

    private static final Logger logger = LoggerFactory.getLogger(VendorServicveImp.class);

    @Autowired
    private CommanUtil commanUtil;

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public PageResponseModel vendorlist(PageDetailModel model) {
        try {
            model = commanUtil.fillValueToPageModel(model);
            Pageable page = commanUtil.getPageDetail(model);
            Page<VendorDTO> dto;

            if (commanUtil.checkNull(model.getSearchKeyword()))
                dto = vendorRepository.findALL(page);
            else
                dto = vendorRepository.findAllBySearch("%" + model.getSearchKeyword() + "%", page);

            return commanUtil.create(Message.SUCCESS, dto.getContent(), commanUtil.pagersultModel(dto), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Exception Will Vendor listing {}", e.getMessage());
            return commanUtil.create(Message.SOMTHING_WRONG, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel updateStatus(ActiveInactiveModel model) {
        Vendor vendor = vendorRepository.findById(model.getId()).orElse(null);
        if (vendor != null) {
            vendor.setStatus(model.getStatus());
            vendor.setUpdated_by(commanUtil.getCurrentUserEmail());
            vendorRepository.save(vendor);
            return commanUtil.create(Message.SUCCESS, vendor, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.VENDOR_NOT_XIST, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel delete(String id) {
        int customer = vendorRepository.countById(id);
        if (customer != 0) {
            vendorRepository.deleteById(id);
            return commanUtil.create(Message.SUCCESS, null, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.VENDOR_NOT_XIST, null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseModel viewVendor(String id) {
        try {
            VendorDetailDTO vendorDetailDTO = vendorRepository.findAllByid(id);
            return commanUtil.create(Message.SUCCESS, vendorDetailDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception Will Customer View Profile");
            return commanUtil.create(Message.SOMTHING_WRONG, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
