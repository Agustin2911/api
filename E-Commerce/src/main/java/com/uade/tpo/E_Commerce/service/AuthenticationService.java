package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.controllers.auth.*;
import com.uade.tpo.E_Commerce.controllers.config.UserPrincipal;
import com.uade.tpo.E_Commerce.entity.Buyer_User;
import com.uade.tpo.E_Commerce.entity.Seller_User;
import com.uade.tpo.E_Commerce.entity.User_Roles;
import com.uade.tpo.E_Commerce.entity.dto.FailedResponse;
import com.uade.tpo.E_Commerce.entity.dto.SuccesResponse;
import com.uade.tpo.E_Commerce.repository.Buyer_UserRepository;
import com.uade.tpo.E_Commerce.repository.Seller_UserRepository;
import com.uade.tpo.E_Commerce.repository.UserRolesRepository;
import io.micrometer.core.ipc.http.HttpSender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.E_Commerce.controllers.config.JwtService;
import com.uade.tpo.E_Commerce.entity.Basic_User;
import com.uade.tpo.E_Commerce.repository.Basic_UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;

@Service
public class AuthenticationService {

    @Value("${upload-dir-seller-user}")
    private String UPLOAD_DIR_seller;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Value("${upload-dir-buyer-user}")
    private String UPLOAD_DIR_buyer;

    @Autowired
    private Buyer_UserRepository buyer_repository;

    @Autowired
    private Seller_UserRepository seller_repository;


    @Autowired
    private final Basic_UserRepository repository;

    @Autowired
    private final UserRolesRepository repository2;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(Basic_UserRepository repository, UserRolesRepository repository2, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.repository2 = repository2;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Configuration
    public class PasswordHashGenerator {

        @Bean
        public CommandLineRunner generateAdminHash(PasswordEncoder passwordEncoder) {
            return args -> {
                // Aquí imprime el hash de tu contraseña de admin
                System.out.println("Admin hash: " + passwordEncoder.encode("admin"));
            };
        }
    }

    @Transactional
    public AuthenticationResponse Sellerregister(SellerRegister request) throws IOException {

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        repository.insertUser(request.getFirstname(),request.getEmail(),hashedPassword);
        entityManager.flush();
        entityManager.clear();
        Optional<Basic_User> user=repository.findLatest();
        System.out.println(user.get().getId_user());
        repository2.assignRoleToUser(user.get().getId_user(),request.getRole());
        entityManager.flush();
        entityManager.clear();
        Optional<User_Roles> role=repository2.findByUserId(user.get().getId_user());
        Optional<Basic_User> updatedUser=repository.findWithRolesById(user.get().getId_user());
        boolean result=CreateSellerUser(request,user.get().getId_user());

        if(result){
            logger.info("todo ok");
        }

        updatedUser.get().setUser_roles(role.get());
        Optional<Seller_User> seller_user=seller_repository.findByIdUser(updatedUser.get().getId_user());

        var jwtToken = jwtService.generateToken(new UserPrincipal(updatedUser.get()));
        return new AuthenticationResponse(jwtToken,updatedUser.get().getId_user(),seller_user.get().getPhoto_url(),"seller");
    }

    @Transactional
    public AuthenticationResponse BuyerRegister(BuyerRegister request) throws IOException{
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        repository.insertUser(request.getFirstname(),request.getEmail(),hashedPassword);
        entityManager.flush();
        entityManager.clear();
        Optional<Basic_User> user=repository.findLatest();
        System.out.println(user.get().getId_user());
        repository2.assignRoleToUser(user.get().getId_user(),request.getRole());
        entityManager.flush();
        entityManager.clear();
        Optional<User_Roles> role=repository2.findByUserId(user.get().getId_user());
        Optional<Basic_User> updatedUser=repository.findWithRolesById(user.get().getId_user());
        boolean result=CreateBuyerUser(request,user.get().getId_user());

        if(result){
            logger.info("todo ok");
        }

        updatedUser.get().setUser_roles(role.get());
        Optional<Buyer_User> buyer=buyer_repository.findByIdUser(updatedUser.get().getId_user());

        logger.info((buyer.get().getLast_name()));
        var jwtToken = jwtService.generateToken(new UserPrincipal(updatedUser.get()));
        return new AuthenticationResponse(jwtToken,updatedUser.get().getId_user(),buyer.get().getPhoto_url(),"buyer");

    }




    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        Optional<Basic_User> user = repository.findByMail(request.getEmail());
        Optional<Seller_User> seller_user=seller_repository.findByIdUser(user.get().getId_user());
        if(seller_user.isPresent()){
            var jwtToken = jwtService.generateToken(new UserPrincipal(user.get()));
            return new AuthenticationResponse(jwtToken,seller_user.get().getId(),seller_user.get().getPhoto_url(),"seller");

        }
        Optional<Buyer_User> buyer_user=buyer_repository.findByIdUser(user.get().getId_user());
        if (buyer_user.isPresent()) {
            var jwtToken = jwtService.generateToken(new UserPrincipal(user.get()));
            return new AuthenticationResponse(jwtToken,user.get().getId_user(),buyer_user.get().getPhoto_url(),"buyer");

        }
        else {
            var jwtToken = jwtService.generateToken(new UserPrincipal(user.get()));
            // Si tu entidad Basic_User no tiene foto, pásale null o un default
            return new AuthenticationResponse(
                    jwtToken,
                    user.get().getId_user(),
                    "",  // o null
                    "admin"
            );
        }
    }


    @Transactional
    public Boolean CreateSellerUser(SellerRegister request,long id_user) throws IOException {


        MultipartFile file = request.getFile();
        String filePath = null;
        String new_name=null;
        logger.info("aca deberia estar la foto: "+String.valueOf(file));
        // Solo generás la ruta si el archivo existe y no está vacío
        if (file != null ) {
            new_name=System.currentTimeMillis() + "_" + file.getOriginalFilename();
            filePath = UPLOAD_DIR_seller + new_name;

            File uploadDir = new File(UPLOAD_DIR_seller);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File destination = new File(filePath);
            file.transferTo(destination);
            logger.info(filePath);
        }

        seller_repository.insertUser(id_user, request.getCuit(), request.getCompanyName(), request.getDescription(), request.getState(),"http://localhost:1273"+"/images/seller_user/"+new_name);
        // Se asume que el seller_user creado se identifica mediante el id_user que se pasa.
        Optional<Seller_User> seller_user = seller_repository.findByIdUser(id_user);
        if (seller_user.isPresent()) {
            return true;
        } else {
            return false;
        }


    }

    @Transactional
    public Boolean CreateBuyerUser(BuyerRegister request,long id_user) throws IOException{

        MultipartFile file = request.getFile();
        String filePath = null;
        String new_name=null;
        logger.info("aca deberia estar la foto: "+String.valueOf(file));
        if (file != null) {
            new_name=System.currentTimeMillis() + "_" + file.getOriginalFilename();
            filePath = UPLOAD_DIR_buyer + new_name;

            logger.info("pase");
            File uploadDir = new File(UPLOAD_DIR_buyer);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File destination = new File(filePath);
            file.transferTo(destination);
            logger.info(filePath);
        }

        buyer_repository.insertUser(id_user, request.getName(), request.getLast_name(), request.getDni(),"http://localhost:1273"+"/images/buyer_user/"+new_name);

        Optional<Buyer_User> buyer_user = buyer_repository.findByIdUser(id_user);
        if (buyer_user.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
