package com.example.adminpanel.controller;

import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("add/{name}")
    public ResponseModel addCategory(@PathVariable("name") String name) {
        return categoryService.addCategory(name);
    }

    @GetMapping("name/list")
    public ResponseModel getNameList() {
        return categoryService.listCategoryName();
    }
}
