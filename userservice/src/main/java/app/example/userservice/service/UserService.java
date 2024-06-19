package app.example.userservice.service;

import app.example.userservice.model.UserModel;
import app.example.userservice.repository.UserRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, UserModel> kafkaTemplate;

    private static final String TOPIC = "user-events";

    public UserModel createUser(UserModel userModel) {
        UserModel createdUserModel = userRepository.save(userModel);
        kafkaTemplate.send(TOPIC, createdUserModel);
        return createdUserModel;
    }

    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
