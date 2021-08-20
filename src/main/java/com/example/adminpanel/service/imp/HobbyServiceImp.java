package com.example.adminpanel.service.imp;

import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.repository.HobbyRepository;
import com.example.adminpanel.service.HobbyService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.Hobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyServiceImp implements HobbyService {

    private static final Logger logger = LoggerFactory.getLogger(HobbyServiceImp.class);

    @Autowired
    private CommanUtil commanUtil;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Override
    public ResponseModel addHobby(String name) {
        int exist = hobbyRepository.countByName(name);
        if (exist == 0) {
            Hobby hobby = new Hobby();
            hobby.setName(name);
            hobbyRepository.save(hobby);
            return commanUtil.create(Message.SUCCESS, hobby, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.HOBBY_EXIST, null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseModel listHobbyNames() {
        try {
            List<String> hobbys = hobbyRepository.findAllName();
            return commanUtil.create(Message.SUCCESS, hobbys, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("listing Hobby Names {}", e.getMessage());
            return commanUtil.create(Message.SOMTHING_WRONG, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
