package com.example.Hexagonal_architecture_pattern.observer;

import com.example.Hexagonal_architecture_pattern.dto.EventDTO;

public interface Observer {
    void update(EventDTO event);
}