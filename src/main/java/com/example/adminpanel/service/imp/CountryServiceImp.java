package com.example.adminpanel.service.imp;

import com.example.adminpanel.controller.AdminController;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.repository.CountryRepository;
import com.example.adminpanel.service.CountryService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImp implements CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImp.class);

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CommanUtil commanUtil;

    @Override
    public ResponseModel addCountry(String name) {
        int exist = countryRepository.countByName(name.toUpperCase());
        if (exist == 0) {
            Country c = new Country();
            c.setName(name);
            countryRepository.save(c);

            return commanUtil.create(Message.COUNTRY_ADDED, c, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.COUNTRY_EXIST, null, HttpStatus.BAD_REQUEST);
        }
    }
}
