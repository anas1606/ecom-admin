package com.example.adminpanel.service.imp;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.PageResponseModel;
import com.example.adminpanel.model.PageDetailModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.model.customer.CustomerDTO;
import com.example.adminpanel.model.customer.CustomerDetailDTO;
import com.example.adminpanel.repository.CustomerAddressRespository;
import com.example.adminpanel.repository.CustomerHobbyRepository;
import com.example.adminpanel.repository.CustomerRepository;
import com.example.adminpanel.repository.OrderDetailRepository;
import com.example.adminpanel.service.CustomerService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImp.class);

    @Autowired
    private CommanUtil commanUtil;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerHobbyRepository customerHobbyRepository;

    @Autowired
    private CustomerAddressRespository customerAddressRespository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public PageResponseModel coustomerlist(PageDetailModel model) {
        try {
            model = commanUtil.fillValueToPageModel(model);
            Pageable page = commanUtil.getPageDetail(model);
            Page<CustomerDTO> dto;
            String search = "%" + model.getSearchKeyword() + "%";
            if (model.getStatus() == -1)
                dto = customerRepository.findALL(search,page);
            else
                dto = customerRepository.findAllBySearch(search,model.getStatus(), page);

            return commanUtil.create(Message.SUCCESS, dto.getContent(), commanUtil.pagersultModel(dto), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Exception Will Customer listing {}", e.getMessage());
            return commanUtil.create(Message.SOMTHING_WRONG, null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel updateStatus(ActiveInactiveModel model) {
        Customer customer = customerRepository.findById(model.getId()).orElse(null);
        if (customer != null) {
            customer.setStatus(model.getStatus());
            customer.setUpdated_by(commanUtil.getCurrentUserEmail());
            customerRepository.save(customer);
            return commanUtil.create(Message.SUCCESS, customer, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.CUSTOMER_NOT_XIST, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseModel delete(String id) {
        int customer = customerRepository.countById(id);
        if (customer != 0) {

//            Delete Customer orders
            orderDetailRepository.deleteByCustomer_Id(id);
//            Delete Customer Address
            customerAddressRespository.deleteByCustomer_Id(id);
//            Delete Customer Hobbys
            customerHobbyRepository.deleteByCustomer_Id(id);
//            finally delete the customer
            customerRepository.deleteById(id);

            return commanUtil.create(Message.SUCCESS, null, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.CUSTOMER_NOT_XIST, null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseModel viewCustomer(String id) {
        try {
            CustomerDetailDTO customerDetailDTO = customerRepository.findAllByid(id);
            List<String> hobbys = customerHobbyRepository.findByid(id);
            customerDetailDTO.setHobby(hobbys);

            return commanUtil.create(Message.SUCCESS, customerDetailDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception Will Customer View Profile");
            return commanUtil.create(Message.SOMTHING_WRONG, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
