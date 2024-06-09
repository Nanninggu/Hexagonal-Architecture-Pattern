package com.example.Hexagonal_architecture_pattern.service;

import com.example.Hexagonal_architecture_pattern.dto.EventDTO;
import com.example.Hexagonal_architecture_pattern.mapper.EventMapper;
import com.example.Hexagonal_architecture_pattern.observer.ConcreteObserver;
import com.example.Hexagonal_architecture_pattern.observer.NewObserver;
import com.example.Hexagonal_architecture_pattern.observer.Observer;
import com.example.Hexagonal_architecture_pattern.port.EventPort;
import com.example.Hexagonal_architecture_pattern.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService implements EventPort, Subject {
    private final EventMapper eventMapper;
    private final List<Observer> observers = new ArrayList<>();

    public EventService(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
        // Create a new instance of the NewObserver
        Observer newObserver = new NewObserver();
        Observer observer = new ConcreteObserver();
        // Register the new observer with the EventService
        registerObserver(newObserver);
        registerObserver(observer);
    }

    @Override
    public void processEvent(EventDTO event) {
        eventMapper.insertEvent(event);
        notifyObservers(event);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(EventDTO event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
}
