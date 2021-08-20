package com.example.adminpanel.controller;

import com.example.adminpanel.model.ActiveInactiveModel;
import com.example.adminpanel.model.ResponseModel;
import com.example.adminpanel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/{name}")
    public ResponseModel addCategory(@PathVariable("name") String name) {
        return categoryService.addCategory(name);
    }

    @GetMapping("")
    public ResponseModel getNameList() {
        return categoryService.list();
    }

    @PutMapping("")
    public ResponseModel updateCategory(@RequestBody ActiveInactiveModel model) {
        return categoryService.update(model);
    }
}
