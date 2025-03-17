package com.example.user_api.service;

import com.example.user_api.data.UserProjection;
import com.example.user_api.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    UserRepository userRepository;
    @Autowired
    public EmployeeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserProjection finduserById(int id){
        return userRepository.findUserById(id).orElseThrow(
                ()-> new RuntimeException("no user with this id")
        );
    }


}
