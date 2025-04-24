package com.example.user_api.controller;

import com.example.user_api.data.UserProjection;
import com.example.user_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
@CrossOrigin(origins="*")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public UserProjection findUserById(@RequestParam int id){
        return employeeService.finduserById(id);
    }
}
