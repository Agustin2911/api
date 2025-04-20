package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Roles;
import com.uade.tpo.E_Commerce.entity.User_Roles;

import java.util.List;
import java.util.Optional;

public interface RolesServiceImp {

    Optional<List<Roles>> getAllRoles();
    Optional<Roles> getRoleById(long id);
    Optional<Roles> createRole(Roles role);
    Optional<Roles> updateRole( Roles role);
    boolean deleteRole(long id);

    // Para la asignación a usuario:
    Optional<Roles> getRoleByUser(long idUser);
    Optional<User_Roles> assignRoleToUser(long idUser, long idRole);
    boolean removeRoleFromUser(long idUser);
}