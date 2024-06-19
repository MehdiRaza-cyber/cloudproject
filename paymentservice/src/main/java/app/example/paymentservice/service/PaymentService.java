package app.example.paymentservice.service;

import app.example.paymentservice.model.Payment;
import app.example.paymentservice.repository.PaymentRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    private static final String TOPIC = "payment-events";

    public Payment createPayment(Payment payment) {
        Payment createdPayment = paymentRepository.save(payment);
        kafkaTemplate.send(TOPIC, createdPayment);
        return createdPayment;
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
    }
}
