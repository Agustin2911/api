package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Roles;
import com.uade.tpo.E_Commerce.entity.User_Roles;
import com.uade.tpo.E_Commerce.repository.RolesRepository;
import com.uade.tpo.E_Commerce.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



@Service
public class RolesService implements RolesServiceImp {

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<Roles>> getAllRoles() {
        return rolesRepository.findAllRoles();
    }

    @Override
    public Optional<Roles> getRoleById(long id) {
        return rolesRepository.findRoleById(id);
    }

    @Override
    @Transactional
    public Optional<Roles> createRole(Roles role) {
        // Insertar utilizando query nativo.
        int rows = rolesRepository.insertRole(role.getRoleName());
        entityManager.flush();
        if (rows > 0) {
            // Recuperar el último rol insertado (precaución si hay concurrencia)
            return rolesRepository.findLatestRole();
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Roles> updateRole(Roles role) {
        Optional<Roles> exist = rolesRepository.findRoleById(role.getId_role());
        if (!exist.isPresent()) {
            return Optional.empty();
        }
        int rows = rolesRepository.updateRole(role.getRoleName(), role.getId_role());
        if (rows > 0) {
            entityManager.flush();
            entityManager.clear();
            return rolesRepository.findRoleById(role.getId_role());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean deleteRole(long id) {
        Optional<Roles> exist = rolesRepository.findRoleById(id);
        if (!exist.isPresent()) {
            return false;
        }
        try {
            int rows = rolesRepository.deleteRole(id);
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Roles> getRoleByUser(long idUser) {
        Optional<User_Roles> userRole = userRolesRepository.findByUserId(idUser);
        if (userRole.isPresent()) {
            return Optional.of(userRole.get().getRoles());
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User_Roles> assignRoleToUser(long idUser, long idRole) {
        int rows = userRolesRepository.assignRoleToUser(idUser, idRole);
        if (rows > 0) {
            return userRolesRepository.findByUserId(idUser);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean removeRoleFromUser(long idUser) {
        try {
            int rows = userRolesRepository.removeRoleFromUser(idUser);
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}