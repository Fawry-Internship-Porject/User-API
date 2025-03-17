package com.example.user_api.service;

import com.example.user_api.data.User;
import com.example.user_api.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getUserHistory(int id){
        return userRepository.getUserHistory(id);
    }
}
