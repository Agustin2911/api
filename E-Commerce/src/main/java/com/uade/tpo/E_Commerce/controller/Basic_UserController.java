package com.uade.tpo.E_Commerce.controllers;

import com.uade.tpo.E_Commerce.entity.Basic_User;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.entity.dto.newBasic_user;

import com.uade.tpo.E_Commerce.service.Basic_UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/basic_user")
public class Basic_UserController {

    @Autowired
    private Basic_UserService service;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        Optional<List<Basic_User>> users = service.getAll();
        if (users.isPresent()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("No users found"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        Optional<Basic_User> user = service.getById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("User not found"));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody newBasic_user user) {
        Optional<Basic_User> created = service.createUser(user);
        if (created.isPresent()) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("User could not be created"));
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody Basic_User user) {
        Optional<Basic_User> updated = service.updateUser( user);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("User could not be updated"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        boolean deleted = service.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok(new SuccesResponse("User deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("User could not be deleted"));
        }
    }
}