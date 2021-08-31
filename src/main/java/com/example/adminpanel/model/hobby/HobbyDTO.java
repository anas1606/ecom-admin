package com.example.adminpanel.model.hobby;

import com.example.commanentity.Hobby;
import lombok.Data;

@Data
public class HobbyDTO {
    private String id;
    private String name;
    private int status;
    private String updatedBy;

    public HobbyDTO(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.status = hobby.getStatus();
        this.updatedBy = hobby.getUpdated_by();
    }
}
