package com.example.Hexagonal_architecture_pattern.observer;

import com.example.Hexagonal_architecture_pattern.dto.EventDTO;

public class NewObserver implements Observer {
    @Override
    public void update(EventDTO event) {
        // Do something with the event
        System.out.println("New Observer received event: " + event.getEvent_type() + " with data: " + event.getData());
    }
}
