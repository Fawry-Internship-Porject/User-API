package com.example.user_api.controller;

import com.example.user_api.data.User;
import com.example.user_api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@CrossOrigin(origins="*")
public class LoginController {
    LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @GetMapping
    public ResponseEntity<?> login(@RequestParam String email , @RequestParam String password){
        return  ResponseEntity.ok(loginService.login(email, password));
    }
}
