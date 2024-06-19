package app.example.bookingservice.service;

import app.example.bookingservice.model.Booking;
import app.example.bookingservice.repository.BookingRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private KafkaTemplate<String, Booking> kafkaTemplate;

    private static final String TOPIC = "booking-events";

    public Booking createBooking(Booking booking) {
        Booking createdBooking = bookingRepository.save(booking);
        kafkaTemplate.send(TOPIC, createdBooking);
        return createdBooking;
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }
}
