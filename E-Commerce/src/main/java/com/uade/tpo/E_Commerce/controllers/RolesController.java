package com.uade.tpo.E_Commerce.controllers;

import com.uade.tpo.E_Commerce.entity.Roles;
import com.uade.tpo.E_Commerce.entity.User_Roles;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.service.RolesServiceImp;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesServiceImp rolesService;


    // GET /roles - Obtener todos los roles
    @GetMapping
    public ResponseEntity<Object> getAllRoles() {
        Optional<List<Roles>> roles = rolesService.getAllRoles();
        if (roles.isPresent() && !roles.get().isEmpty()) {
            return ResponseEntity.ok(roles.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("No roles found"));
        }
    }

    // GET /roles/{id} - Obtener un rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoleById(@PathVariable long id) {
        Optional<Roles> role = rolesService.getRoleById(id);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Role not found"));
        }
    }

    // POST /roles - Crear un rol
    @PostMapping
    public ResponseEntity<Object> createRole(@RequestBody Roles role) {
        Optional<Roles> created = rolesService.createRole(role);
        if (created.isPresent()) {
            return ResponseEntity.ok(created.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Could not create role"));
        }
    }

    // PUT /roles/{id} - Actualizar un rol
    @PutMapping
    public ResponseEntity<Object> updateRole( @RequestBody Roles role) {
        Optional<Roles> updated = rolesService.updateRole(role);
        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Could not update role"));
        }
    }

    // DELETE /roles/{id} - Eliminar un rol
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable long id) {
        boolean deleted = rolesService.deleteRole(id);
        if (deleted) {
            return ResponseEntity.ok(new SuccesResponse("Role deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Could not delete role"));
        }
    }

    // GET /roles/user/{userId} - Obtener el rol asignado a un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getRoleByUser(@PathVariable long userId) {
        Optional<Roles> role = rolesService.getRoleByUser(userId);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("No role assigned for this user"));
        }
    }

    // POST /roles/{roleId}/assign/{userId} - Asignar un rol a un usuario
    @PostMapping("/assign/{roleId}/{userId}")
    public ResponseEntity<Object> assignRole(@PathVariable long roleId, @PathVariable long userId) {
        Optional<User_Roles> assigned = rolesService.assignRoleToUser(userId, roleId);
        if (assigned.isPresent()) {
            return ResponseEntity.ok(assigned.get());
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Could not assign role to user"));
        }
    }

    // DELETE /roles/user/{userId} - Remover el rol asignado a un usuario
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Object> removeRole(@PathVariable long userId) {
        boolean removed = rolesService.removeRoleFromUser(userId);
        if (removed) {
            return ResponseEntity.ok(new SuccesResponse("Role removed from user successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new FailedResponse("Could not remove role from user"));
        }
    }
}

