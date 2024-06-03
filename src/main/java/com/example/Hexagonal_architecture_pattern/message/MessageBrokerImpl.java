package com.example.Hexagonal_architecture_pattern.message;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class MessageBrokerImpl implements MessageBroker {
    private final JdbcTemplate jdbcTemplate;

    public MessageBrokerImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void send(String message) {
        // Implement this method based on your requirements
        // For example, you might send the message to a specific queue or topic
    }
}
