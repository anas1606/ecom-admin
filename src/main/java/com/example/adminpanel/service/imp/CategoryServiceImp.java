package com.example.adminpanel.service.imp;

import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.repository.CategoryRepository;
import com.example.adminpanel.service.CategoryService;
import com.example.adminpanel.util.CommanUtil;
import com.example.adminpanel.util.Message;
import com.example.commanentity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService {

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
}
