package com.example.user_api.controller;

import com.example.user_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
@CrossOrigin(origins="*")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("find")
    public ResponseEntity<?> findUserById(@RequestParam int id){
            return ResponseEntity.ok(employeeService.finduserById(id));
    }
}
