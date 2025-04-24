package com.example.user_api.controller;

import com.example.user_api.data.User;
import com.example.user_api.data.UserAuditDTO;
import com.example.user_api.data.UserProjection;
import com.example.user_api.data.UserRepository;
import com.example.user_api.service.LdapUserService;
import com.example.user_api.service.UserAuditService;
import com.example.user_api.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("managers")
@CrossOrigin(origins = "*")
public class ManagerController {
    private final ManagerService managerService;
    private final UserAuditService userAuditService;
    private final LdapUserService ldapUserService;
    private final UserRepository userRepository;

    @Autowired
    public ManagerController(ManagerService managerService, UserAuditService userAuditService, LdapUserService ldapUserService, UserRepository userRepository) {
        this.managerService = managerService;
        this.userAuditService = userAuditService;
        this.ldapUserService = ldapUserService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id) {
        return managerService.findUserById(id);
    }

    @GetMapping("/{id}/history")
    public List<UserAuditDTO> getUserHistory(@PathVariable int id) {
        return userAuditService.getUserAuditHistory(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        managerService.addUser(user);
        ldapUserService.add(user.getMail(), user.getPassword(), user.getRole());
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<UserProjection> userProjection = userRepository.findUserById(id);
        ldapUserService.modify(userProjection.get().getMail(), user.getPassword(), user.getRole());
        managerService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        Optional<UserProjection> userProjection = userRepository.findUserById(id);
        managerService.deleteUser(id);
        ldapUserService.delete(userProjection.get().getMail());
    }

}
