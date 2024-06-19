package app.example.userservice.controller;

import app.example.userservice.model.UserModel;
import app.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        UserModel createdUserModel = userService.createUser(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Long id) {
        UserModel userModel = userService.getUserById(id);
        return ResponseEntity.ok(userModel);
    }
}
