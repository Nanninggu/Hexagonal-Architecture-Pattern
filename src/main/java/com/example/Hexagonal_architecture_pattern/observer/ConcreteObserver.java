package com.example.Hexagonal_architecture_pattern.observer;

import com.example.Hexagonal_architecture_pattern.dto.EventDTO;
import com.example.Hexagonal_architecture_pattern.mapper.EventMapperConcrete;

public class ConcreteObserver implements Observer {
    private final EventMapperConcrete eventMapperConcrete;

    public ConcreteObserver(EventMapperConcrete eventMapperConcrete) {
        this.eventMapperConcrete = eventMapperConcrete;
    }

    @Override
    public void update(EventDTO event) {
        // Do something with the event
        System.out.println("Received event: " + event.getEvent_type() + " with data: " + event.getData());

        // Insert the event into the database
        eventMapperConcrete.insertEvent(event);
    }
}
