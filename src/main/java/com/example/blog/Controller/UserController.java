package com.example.blog.Controller;

import com.example.blog.Model.User;
import com.example.blog.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
   private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(authService.getUsers());
    }

    @PostMapping("/register-user")
    public ResponseEntity register(@Valid @RequestBody User user) {
        authService.registerUser(user);
        return ResponseEntity.status(200).body("user registered successfully");
    }
    @PutMapping("/update-user/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
        authService.updateUser(id, user);
        return ResponseEntity.status(200).body("User updated successfully");
    }
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        authService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

}
