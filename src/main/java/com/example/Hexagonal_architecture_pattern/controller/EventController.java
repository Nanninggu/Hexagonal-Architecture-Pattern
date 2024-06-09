package com.example.Hexagonal_architecture_pattern.controller;

import com.example.Hexagonal_architecture_pattern.dto.EventDTO;
import com.example.Hexagonal_architecture_pattern.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/events")
    public void handleEvent(@RequestBody EventDTO event) {
        eventService.processEvent(event);
    }
}
