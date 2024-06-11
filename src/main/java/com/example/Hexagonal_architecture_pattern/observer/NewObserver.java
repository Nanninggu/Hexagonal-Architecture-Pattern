package com.example.Hexagonal_architecture_pattern.observer;

import com.example.Hexagonal_architecture_pattern.dto.EventDTO;
import com.example.Hexagonal_architecture_pattern.mapper.EventMapperNew;

public class NewObserver implements Observer {
    private final EventMapperNew eventMapperNew;

    public NewObserver(EventMapperNew eventMapperNew) {
        this.eventMapperNew = eventMapperNew;
    }

    @Override
    public void update(EventDTO event) {
        // Do something with the event
        System.out.println("Received event: " + event.getEvent_type() + " with data: " + event.getData());

        // Insert the event into the database
        eventMapperNew.insertEvent(event);
    }
}
