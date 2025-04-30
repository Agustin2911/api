package com.uade.tpo.E_Commerce.controllers;

import com.uade.tpo.E_Commerce.entity.Seller_User;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.entity.dto.newSeller_User;
import com.uade.tpo.E_Commerce.service.Seller_UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/seller_user")
public class Seller_UserController {

    @Value("${upload-dir-seller-user}")
    private String UPLOAD_DIR;

    @Autowired
    private Seller_UserService service;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        Optional<List<Seller_User>> users = service.getAll();
        if (users.isPresent() && !users.get().isEmpty()) {
            return ResponseEntity.ok(users.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("No seller users found"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id) {
        Optional<Seller_User> user = service.getById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Seller user not found"));
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createUser(@ModelAttribute newSeller_User user) throws IOException  {
        String filePath = UPLOAD_DIR + System.currentTimeMillis() + "_" + user.getFile().getOriginalFilename();

        Optional<Seller_User> created = service.createUser(user,filePath);
        if (created.isPresent()) {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File destination = new File(filePath);
            user.getFile().transferTo(destination);
            return ResponseEntity.ok(new SuccesResponse("user created succesfully"));
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Seller user could not be created"));
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody Seller_User user) {
        Optional<Seller_User> updated = service.updateUser(user);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Seller user could not be updated"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        boolean deleted = service.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok(new SuccesResponse("Seller user deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Seller user could not be deleted"));
        }
    }
}

