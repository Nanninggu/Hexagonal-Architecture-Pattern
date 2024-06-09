package com.example.Hexagonal_architecture_pattern.port;

import com.example.Hexagonal_architecture_pattern.dto.EventDTO;

public interface EventPort {
    void processEvent(EventDTO event);
}
