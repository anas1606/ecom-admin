package com.example.adminpanel.service.imp;

import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.repository.CategoryRepository;
import com.example.adminpanel.repository.CountryRepository;
import com.example.adminpanel.repository.HobbyRepository;
import com.example.adminpanel.repository.StateRepository;
import com.example.adminpanel.service.DropdownService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DropDownServiceImp implements DropdownService {

    private static final Logger logger = LoggerFactory.getLogger(DropDownServiceImp.class);

    @Autowired
    private CommanUtil commanUtil;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public ResponseModel country() {
        try {
            return commanUtil.create(Message.SUCCESS
                    , countryRepository.findAllByStatus()
                    , HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception Will geting Country List {}", e.getMessage());
            return commanUtil.create(Message.SUCCESS, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel state(String name) {
        try {
            return commanUtil.create(Message.SUCCESS
                    , stateRepository.findAllByCountry(name)
                    , HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception Will geting State List by Country {}", e.getMessage());
            return commanUtil.create(Message.SUCCESS, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel hobby() {
        try {
            return commanUtil.create(Message.SUCCESS
                    , hobbyRepository.findAllName()
                    , HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception Will getting Hobby List {}", e.getMessage());
            return commanUtil.create(Message.SUCCESS, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel category() {
        try {
            return commanUtil.create(Message.SUCCESS
                    , categoryRepository.findAllName()
                    , HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception Will getting category List {}", e.getMessage());
            return commanUtil.create(Message.SUCCESS, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
