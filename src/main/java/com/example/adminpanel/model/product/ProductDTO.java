package com.example.adminpanel.model.product;

import com.example.commanentity.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private String id;
    private String name;
    private Double price;
    private String desc;
    private int status;
    private String updatedBy;
    private String category;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.desc = product.getDescription();
        this.status = product.getStatus();
        this.updatedBy = product.getUpdated_by();
        this.category = product.getCategory().getName();
    }

}
