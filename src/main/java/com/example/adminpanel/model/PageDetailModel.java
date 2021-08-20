package com.example.adminpanel.model;

import lombok.Data;

@Data
public class PageDetailModel {
    private int page;
    private int limit;
    private String sortorder;
    private String sortFileld;
    private String searchKeyword;
}
