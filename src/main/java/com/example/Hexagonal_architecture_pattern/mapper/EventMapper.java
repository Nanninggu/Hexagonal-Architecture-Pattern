package com.example.Hexagonal_architecture_pattern.mapper;

import com.example.Hexagonal_architecture_pattern.dto.EventDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {
    @Insert("INSERT INTO event_001 (event_type, data) VALUES (#{event_type}, #{data})")
    void insertEvent(EventDTO event);
}
