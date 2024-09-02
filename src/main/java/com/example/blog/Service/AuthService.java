package com.example.blog.Service;

import com.example.blog.Api.ApiException;
import com.example.blog.Model.User;
import com.example.blog.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public List<User> getUsers() {
        return authRepository.findAll();
    }

    public void registerUser(User user) {
        user.setRole("USER");
        String hash= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
    }

    public void updateUser(Integer id,User user) {
        User u = authRepository.findUserById(id);
       if(u == null) {
            throw new ApiException("user not found");

        }
        user.setRole("USER");
        String hash= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);

        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        authRepository.save(u);

    }

    public void deleteUser(Integer id) {
        User user = authRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User not found");
        }
        authRepository.delete(user);
    }

}
