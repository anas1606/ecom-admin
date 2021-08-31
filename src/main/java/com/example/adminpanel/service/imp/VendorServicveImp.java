package com.example.adminpanel.service.imp;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.model.product.ProductDTO;
import com.example.adminpanel.model.vendor.VendorDTO;
import com.example.adminpanel.model.vendor.VendorDetailDTO;
import com.example.adminpanel.repository.ProductRepository;
import com.example.adminpanel.repository.VendorRepository;
import com.example.adminpanel.service.VendorService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.Product;
import com.example.commanentity.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServicveImp implements VendorService {


    private static final Logger logger = LoggerFactory.getLogger(VendorServicveImp.class);

    @Autowired
    private CommanUtil commanUtil;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public PageResponseModel vendorlist(PageDetailModel model) {
        try {
            model = commanUtil.fillValueToPageModel(model);
            Pageable page = commanUtil.getPageDetail(model);
            Page<VendorDTO> dto;
            String search = "%" + model.getSearchKeyword() + "%";
            if (model.getStatus() == -1)
                dto = vendorRepository.findALL(search, page);
            else
                dto = vendorRepository.findAllBySearch(search, model.getStatus(), page);

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
    public ResponseModel viewVendor(String id) {
        try {
            VendorDetailDTO vendorDetailDTO = vendorRepository.findAllByid(id);
            return commanUtil.create(Message.SUCCESS, vendorDetailDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception Will Vendor View Profile");
            return commanUtil.create(Message.SOMTHING_WRONG, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public PageResponseModel productList(PageDetailModel model) {
        try {
            model = commanUtil.fillValueToPageModel(model);
            Pageable page = commanUtil.getPageDetail(model);

            Page<ProductDTO> productDTO;
            String search = "%" + model.getSearchKeyword() + "%";
            if (model.getStatus() == -1)
                productDTO = productRepository.findByVendor_Id(model.getId(), search, page);
            else
                productDTO = productRepository.findByVendor_IdAndStatus(model.getId(), search, model.getStatus(), page);

            return commanUtil.create(Message.SUCCESS, productDTO.getContent(), commanUtil.pagersultModel(productDTO), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception Will Vendor Product List");
            return commanUtil.create(Message.SOMTHING_WRONG, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel updateProduct(ActiveInactiveModel model) {
        Product product = productRepository.findById(model.getId()).orElse(null);
        if (product != null) {
            product.setStatus(model.getStatus());
            product.setUpdated_by(commanUtil.getCurrentUserEmail());
            productRepository.save(product);
            return commanUtil.create(Message.SUCCESS, product, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.PRODUCT_NOT_EXIST, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
