package com.example.adminpanel.service.imp;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.repository.CategoryRepository;
import com.example.adminpanel.service.CategoryService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImp.class);

    @Autowired
    private CommanUtil commanUtil;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseModel addCategory(String name) {
        int exist = categoryRepository.countByName(name.toUpperCase());
        if (exist == 0) {

            Category category = new Category();
            category.setName(name.toUpperCase());
            categoryRepository.save(category);

            return commanUtil.create(Message.CATEGORY_ADDED, category, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.CATEGORY_EXIST, null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseModel listCategoryName() {
        try {
            return commanUtil.create(Message.SUCCESS, categoryRepository.findAllByStatus(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception will category Name Fetch {}", e.getMessage());
            return commanUtil.create(Message.SOMTHING_WRONG, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel list() {
        try {
            return commanUtil.create(Message.SUCCESS,
                    categoryRepository.findALL()
                    , HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception Will listing {}", e.getMessage());
            return commanUtil.create(Message.SOMTHING_WRONG, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel updateStatus(ActiveInactiveModel model) {
        Category category = categoryRepository.findById(model.getId()).orElse(null);
        if (category != null) {
            category.setStatus(model.getStatus());
            category.setUpdated_by(commanUtil.getCurrentUserEmail());
            categoryRepository.save(category);

            return commanUtil.create(Message.SUCCESS, category, HttpStatus.OK);
        } else {
            return commanUtil.create(Message.CATEGORY_NOT_EXIST, null, HttpStatus.BAD_REQUEST);
        }
    }
}
