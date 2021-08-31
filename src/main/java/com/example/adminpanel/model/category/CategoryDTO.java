package com.example.adminpanel.model.category;

import com.example.commanentity.Category;
import lombok.Data;

@Data
public class CategoryDTO {
    private String id;
    private String name;
    private int status;
    private String updatedBy;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.status = category.getStatus();
        this.updatedBy = category.getUpdated_by();
    }
}
