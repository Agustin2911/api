package com.uade.tpo.E_Commerce.controllers;

import com.uade.tpo.E_Commerce.entity.Buyer_User;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.entity.dto.newBuyer_User;
import com.uade.tpo.E_Commerce.service.Buyer_UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/buyer_user")
public class Buyer_UserController {

    @Autowired
    private Buyer_UserService service;

    @Value("${upload-dir-buyer-user}")
    private String UPLOAD_DIR;


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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createUser(@ModelAttribute newBuyer_User user) throws IOException {
        MultipartFile file = user.getFile();
        String filePath = null;

        // Verificás si el archivo fue enviado
        if (file != null && !file.isEmpty()) {
            filePath = UPLOAD_DIR + System.currentTimeMillis() + "_" + file.getOriginalFilename();

            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File destination = new File(filePath);
            file.transferTo(destination);
        }

        // Pasás null o el filePath, según corresponda
        Optional<Buyer_User> created = service.createUser(user, filePath);

        if (created.isPresent()) {
            return ResponseEntity.ok(new SuccesResponse("user created successfully"));
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
