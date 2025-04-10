package com.example.user_api.controller;

import com.example.user_api.data.User;
import com.example.user_api.service.UserAuditService;
import com.example.user_api.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager")
@CrossOrigin(origins="*")
public class ManagerController {
    private final ManagerService managerService;
    private final UserAuditService userAuditService;
    @Autowired
    public ManagerController(ManagerService managerService, UserAuditService userAuditService) {
        this.managerService = managerService;
        this.userAuditService = userAuditService;
    }
    @GetMapping("find")
    public ResponseEntity<?> findUserById(@RequestParam int id){
     return ResponseEntity.ok(managerService.findUserById(id));
    }
    @GetMapping("history")
    public ResponseEntity<?> getUserHistory(@RequestParam int id)
    {
        return ResponseEntity.ok(userAuditService.getUserAuditHistory(id));
    }
    @PostMapping("add-user")
    public ResponseEntity<String> addUser(@RequestBody User user){
            managerService.addUser(user);
            return ResponseEntity.ok("User added successfully");
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User user) {
            managerService.updateUser(id, user);
            return ResponseEntity.ok("User updated successfully");
    }
    @DeleteMapping("delete-user")
    public ResponseEntity<String> deleteUser(int id){
            managerService.deleteUser(id);
            return ResponseEntity.ok("user deleted successfully");
    }

}
