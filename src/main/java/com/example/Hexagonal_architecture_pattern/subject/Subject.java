package com.example.Hexagonal_architecture_pattern.subject;

import com.example.Hexagonal_architecture_pattern.dto.EventDTO;
import com.example.Hexagonal_architecture_pattern.observer.Observer;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(EventDTO event);
}
