package com.example.user_api.controller;

import com.example.user_api.data.User;
import com.example.user_api.service.EmployeeService;
import com.example.user_api.service.ManagerService;
import com.example.user_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager")
public class ManagerController {
    private final ManagerService managerService;
    private final UserService userService;
    @Autowired
    public ManagerController(ManagerService managerService, UserService userService) {
        this.managerService = managerService;
        this.userService = userService;
    }
    @GetMapping("find")
    public ResponseEntity<?> findUserById(@RequestParam int id){
        try {

            return ResponseEntity.ok(managerService.findUserById(id));
        }
        catch (RuntimeException e) {
        return ResponseEntity.status(404).body("user not found");
        }
    }
    @PostMapping("add-user")
    public ResponseEntity<String> addUser(@RequestBody User user){
        try {
            managerService.addUser(user);
            return ResponseEntity.ok("User added successfully");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PutMapping("update-user")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        try {
            managerService.updateUser(user);
            return ResponseEntity.ok("user updated successfully");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("User not found");
        }
    }
    @DeleteMapping("delete-user")
    public ResponseEntity<String> deleteUser(int id){
        try {
            managerService.deleteUser(id);
            return ResponseEntity.ok("user deleted successfully");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("User not found");
        }
    }

}
