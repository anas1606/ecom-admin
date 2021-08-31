package com.example.adminpanel.service.imp;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.model.order.OrderDTO;
import com.example.adminpanel.model.order.OrderDetailDTO;
import com.example.adminpanel.model.vendor.VendorDTO;
import com.example.adminpanel.model.vendor.VendorDetailDTO;
import com.example.adminpanel.repository.OrderDetailRepository;
import com.example.adminpanel.service.OrderService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.OrderDetail;
import com.example.commanentity.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImp.class);

    @Autowired
    private CommanUtil commanUtil;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public PageResponseModel list(PageDetailModel model) {
        try {
            model = commanUtil.fillValueToPageModel(model);
            Pageable page = commanUtil.getPageDetail(model);
            Page<OrderDTO> dto;
            String search = "%" + model.getSearchKeyword() + "%";
            if (model.getStatus() == -1)
                dto = orderDetailRepository.findALL(search,page);
            else
                dto = orderDetailRepository.findAllBySearch(search,model.getStatus(), page);

            return commanUtil.create(Message.SUCCESS, dto.getContent(), commanUtil.pagersultModel(dto), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Exception Will Order listing {}", e.getMessage());
            return commanUtil.create(Message.SOMTHING_WRONG, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel view(String id) {
        try {
            OrderDetailDTO orderDetailDTO = orderDetailRepository.findAllByid(id);
            return commanUtil.create(Message.SUCCESS, orderDetailDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception Will Order View");
            return commanUtil.create(Message.SOMTHING_WRONG, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel delete(String id) {
        int exist = orderDetailRepository.countById(id);
        if (exist != 0) {
            orderDetailRepository.deleteById(id);
            return commanUtil.create(Message.SUCCESS, null, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.ORDER_NOT_EXIST, null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseModel updateStatus(ActiveInactiveModel model) {
        OrderDetail order = orderDetailRepository.findById(model.getId()).orElse(null);
        if (order != null) {
            order.setStatus(model.getStatus());
            order.setUpdated_by(commanUtil.getCurrentUserEmail());
            orderDetailRepository.save(order);
            return commanUtil.create(Message.SUCCESS, order, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.ORDER_NOT_EXIST, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
