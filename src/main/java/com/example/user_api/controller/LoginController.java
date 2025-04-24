package com.example.user_api.controller;

import com.example.user_api.data.User;
import com.example.user_api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins="*")
public class LoginController {
    LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @GetMapping
    public User login(@RequestParam String email , @RequestParam String password){
        return  loginService.login(email, password);
    }
}
