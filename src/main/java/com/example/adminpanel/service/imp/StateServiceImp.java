package com.example.adminpanel.service.imp;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.repository.StateRepository;
import com.example.adminpanel.service.StateService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImp implements StateService {

    private static final Logger logger = LoggerFactory.getLogger(StateServiceImp.class);

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

    @Override
    public ResponseModel statenamelist() {
        try {
            return commanUtil.create(Message.SUCCESS, stateRepository.findAllByStatus(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception will State Name Fetch {}", e.getMessage());
            return commanUtil.create(Message.SOMTHING_WRONG, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel stateList() {
        try {
            return commanUtil.create(Message.SUCCESS
                    , stateRepository.findByALL()
                    , HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception Will geting Country List {}", e.getMessage());
            return commanUtil.create(Message.SUCCESS, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel update(ActiveInactiveModel model) {
        State state = stateRepository.findById(model.getId()).orElse(null);
        if (state != null) {
            state.setStatus(model.getStatus());
            state.setName(model.getName().toUpperCase());
            state.setUpdated_by(commanUtil.getCurrentUserEmail());
            stateRepository.save(state);
            logger.info("state Status Updated");

            return commanUtil.create(Message.SUCCESS, state, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.STATE_NOT_EXIST, null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseModel delete(String id) {
        int state = stateRepository.countById(id);
        if (state != 0) {
            stateRepository.deleteById(id);
            logger.info("Country deleted");

            return commanUtil.create(Message.SUCCESS, null, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.COUNTRY_NOT_XIST, null, HttpStatus.BAD_REQUEST);
        }
    }
}
