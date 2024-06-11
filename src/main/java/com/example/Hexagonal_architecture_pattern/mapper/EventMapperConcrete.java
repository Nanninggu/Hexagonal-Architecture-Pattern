package com.example.Hexagonal_architecture_pattern.mapper;

import com.example.Hexagonal_architecture_pattern.dto.EventDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapperConcrete {
    @Insert("INSERT INTO event_003 (event_type, data) VALUES (#{event_type}, #{data})")
    void insertEvent(EventDTO event);
}
