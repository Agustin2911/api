package com.uade.tpo.E_Commerce.controller;

import com.uade.tpo.E_Commerce.entity.Buyer_User;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.entity.dto.newBuyer_User;
import com.uade.tpo.E_Commerce.service.Buyer_UserServiceImp;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/buyer_user")
public class Buyer_UserController {

    @Autowired
    private Buyer_UserServiceImp service;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        Optional<List<Buyer_User>> users = service.getAll();
        if (users.isPresent() && !users.get().isEmpty()) {
            return ResponseEntity.ok(users.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("No buyer users found"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        Optional<Buyer_User> user = service.getById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Buyer user not found"));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody newBuyer_User user) {
        Optional<Buyer_User> created = service.createUser(user);
        if (created.isPresent()) {
            return ResponseEntity.ok(created.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Buyer user could not be created"));
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody Buyer_User user) {
        Optional<Buyer_User> updated = service.updateUser(user);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Buyer user could not be updated"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        boolean deleted = service.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok(new SuccesResponse("Buyer user deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Buyer user could not be deleted"));
        }
    }
}