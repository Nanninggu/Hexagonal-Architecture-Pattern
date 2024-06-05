package com.example.Hexagonal_architecture_pattern.client;

import com.example.Hexagonal_architecture_pattern.dto.PaymentEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PaymentEventClient {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {
        // Create a PaymentEvent object for orderCreated
        PaymentEvent orderCreatedEvent = new PaymentEvent();
        orderCreatedEvent.setOrderId("1234");
        orderCreatedEvent.setAmount(String.valueOf(100.0));
        orderCreatedEvent.setEventType("ORDER_CREATED");

        // Create a PaymentEvent object for paymentCompleted
        PaymentEvent paymentCompletedEvent = new PaymentEvent();
        paymentCompletedEvent.setPaymentId(Integer.parseInt("217"));
        paymentCompletedEvent.setOrderId("1234");
        paymentCompletedEvent.setAmount(String.valueOf(100.0));
        paymentCompletedEvent.setEventType("PAYMENT_COMPLETED");

        // Create a PaymentEvent object for paymentFailed
        PaymentEvent paymentFailedEvent = new PaymentEvent();
        paymentFailedEvent.setPaymentId(Integer.parseInt("217"));
        paymentFailedEvent.setOrderId("1234");
        paymentFailedEvent.setAmount(String.valueOf(100.0));
        paymentFailedEvent.setEventType("PAYMENT_FAILED");

        // Convert the PaymentEvent objects to JSON
        String orderCreatedJson = new ObjectMapper().writeValueAsString(orderCreatedEvent);
        String paymentCompletedJson = new ObjectMapper().writeValueAsString(paymentCompletedEvent);
        String paymentFailedJson = new ObjectMapper().writeValueAsString(paymentFailedEvent);

        // Create the URIs for the endpoints
        URI orderCreatedUri = new URI("http://localhost:8080/payments/orderCreated");
        URI paymentCompletedUri = new URI("http://localhost:8080/payments/paymentCompleted");
        URI paymentFailedUri = new URI("http://localhost:8080/payments/paymentFailed");

        // Create the HttpRequests
        HttpRequest orderCreatedRequest = createPostRequest(orderCreatedUri, orderCreatedJson);
        HttpRequest paymentCompletedRequest = createPostRequest(paymentCompletedUri, paymentCompletedJson);
        HttpRequest paymentFailedRequest = createPostRequest(paymentFailedUri, paymentFailedJson);

        // Send the HttpRequests in order
        sendRequest(orderCreatedRequest);
        sendRequest(paymentCompletedRequest);
        sendRequest(paymentFailedRequest);
    }

    private static HttpRequest createPostRequest(URI uri, String json) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    private static void sendRequest(HttpRequest request) throws Exception {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response status and headers
        System.out.println("Response status: " + response.statusCode());
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // Print the response body
        System.out.println(response.body());
    }
}
