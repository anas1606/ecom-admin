package com.example.adminpanel.model.state;

import com.example.commanentity.State;
import lombok.Data;

@Data
public class StateDTO {
    private String id;
    private String name;
    private int status;
    private String counrty;

    public StateDTO(State state) {
        this.id = state.getId();
        this.status = state.getStatus();
        this.name = state.getName();
        this.counrty = state.getCountry().getName();
    }
}
