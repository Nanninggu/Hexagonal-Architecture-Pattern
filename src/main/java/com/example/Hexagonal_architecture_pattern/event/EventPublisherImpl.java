package com.example.Hexagonal_architecture_pattern.event;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import com.example.Hexagonal_architecture_pattern.message.MessageBroker;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherImpl implements EventPublisher {
    // This could be a message broker like RabbitMQ, Kafka, etc.
    private final MessageBroker messageBroker;

    public EventPublisherImpl(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public void publish(PaymentEvent event) {
        // Convert the event to a format suitable for your message broker
        String message = convertEventToMessage(event);
        // Send the message to the broker
        messageBroker.send(message);
    }

    private String convertEventToMessage(PaymentEvent event) {
        // Implement this method based on your requirements
        // For example, you might convert the event to JSON
        return null;
    }
}
