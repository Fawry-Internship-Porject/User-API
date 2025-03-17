package com.example.user_api.service;

import com.example.user_api.data.User;
import com.example.user_api.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerService {
    private final UserRepository userRepository;
    @Autowired
    public ManagerService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User findUserById(int id){
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));
    }
    public void addUser(User user){
        if(userRepository.existsById(user.getId())) {
        throw new IllegalArgumentException("user already exists");
        }
        userRepository.save(user);
    }
    public void updateUser(int id,User updatedUser){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist in the database"));
        if (updatedUser.getName() != null) existingUser.setName(updatedUser.getName());
        if (updatedUser.getTitle() != null) existingUser.setTitle(updatedUser.getTitle());
        if (updatedUser.getRole() != null) existingUser.setRole(updatedUser.getRole());
        if (updatedUser.getMail() != null) existingUser.setMail(updatedUser.getMail());
        if (updatedUser.getPhone() != null) existingUser.setPhone(updatedUser.getPhone());
        if (updatedUser.getLevel() != null) existingUser.setLevel(updatedUser.getLevel());
        if (updatedUser.getManager() != null) existingUser.setManager(updatedUser.getManager());
        if (updatedUser.getDepartment() != null) existingUser.setDepartment(updatedUser.getDepartment());
        if(updatedUser.getSalaryGross()!=null) existingUser.setSalaryGross(updatedUser.getSalaryGross());

        userRepository.save(existingUser);
    }
    public void deleteUser(int id){
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist");
        }
        userRepository.deleteById(id);
    }
}
