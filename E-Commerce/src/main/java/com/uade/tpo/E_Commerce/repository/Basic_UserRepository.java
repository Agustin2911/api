package com.uade.tpo.E_Commerce.repository;

import com.uade.tpo.E_Commerce.entity.Basic_User;
import com.uade.tpo.E_Commerce.entity.dto.UserRolesRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface Basic_UserRepository extends JpaRepository<Basic_User, Long> {


    @Query(value = "SELECT * FROM Basic_User", nativeQuery = true)
    Optional<List<Basic_User>> findAllUsers();

    @Query(value = "select bu.username, bu.mail, r.role_name from roles r inner join user_roles ur on r.id_role = ur" +
            ".id_role " +
            "inner join basic_user bu on ur.id_user = bu.id_user",
            nativeQuery = true)
    Optional<List<UserRolesRequest>> findRolesOfUsers();

    @Query(value = "SELECT * FROM Basic_User WHERE id_user = ?1", nativeQuery = true)
    Optional<Basic_User> findByIdUser(long id);

    @Query(value = "SELECT * FROM Basic_User WHERE mail = ?1", nativeQuery = true)
    Optional<Basic_User> findByMail(String mail);

    @Query(value = "select id_user from basic_user where mail = ?1", nativeQuery = true)
    Optional<Object> checkMail(String mail);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Basic_User(username, mail, password) VALUES (?1, ?2, ?3)", nativeQuery = true)
    int insertUser(String username, String mail, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Basic_User SET username = ?1, mail = ?2, password = ?3 WHERE id_user = ?4", nativeQuery = true)
    int updateUser(String username, String mail, String password, long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Basic_User WHERE id_user = ?1", nativeQuery = true)
    int deleteUser(long id) ;

    @Query(value = "SELECT * FROM Basic_User ORDER BY id_user DESC LIMIT 1", nativeQuery = true)
    Optional<Basic_User> findLatest();

    @Query("SELECT u FROM basic_user u LEFT JOIN FETCH u.user_roles WHERE u.id_user = ?1")
    Optional<Basic_User> findWithRolesById( Long id);



}
