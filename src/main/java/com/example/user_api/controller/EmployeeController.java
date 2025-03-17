package com.example.user_api.controller;

import com.example.user_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")

public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("find")
    public ResponseEntity<?> findUserById(@RequestParam int id){
        try {
            return ResponseEntity.ok(employeeService.finduserById(id));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(404).body("user not found");
        }
    }
}
