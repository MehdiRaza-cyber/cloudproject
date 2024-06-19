package app.example.notificationservice.listener;

import app.example.notificationservice.service.NotificationService;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaListeners {

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "booking-events", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Message received: " + message);
        notificationService.sendNotification(message);
    }
}