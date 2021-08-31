package com.example.adminpanel.model.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateDTO {
    private String id;
    private String name;
    private int status;
    private String counrty;
    private String updatedBy;
}
