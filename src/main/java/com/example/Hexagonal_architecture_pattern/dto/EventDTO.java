package com.example.Hexagonal_architecture_pattern.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
    private String event_type;
    private String data;

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
