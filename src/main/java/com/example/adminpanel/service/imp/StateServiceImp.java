package com.example.adminpanel.service.imp;

import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.repository.StateRepository;
import com.example.adminpanel.service.StateService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.Country;
import com.example.commanentity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImp implements StateService {

    @Autowired
    private CommanUtil commanUtil;
    @Autowired
    private StateRepository stateRepository;

    @Override
    public ResponseModel addState(String name) {
        int exist = stateRepository.countByName(name.toUpperCase());
        if (exist == 0) {
            State s = new State();
            s.setName(name.toUpperCase());
            stateRepository.save(s);

            return commanUtil.create(Message.STATE_ADDED, s, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.STATE_EXIST, null, HttpStatus.BAD_REQUEST);
        }
    }
}
