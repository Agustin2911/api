package com.uade.tpo.E_Commerce.service;

import com.uade.tpo.E_Commerce.entity.Company_Shops;
import com.uade.tpo.E_Commerce.entity.Sale;
import com.uade.tpo.E_Commerce.entity.Seller_User;
import com.uade.tpo.E_Commerce.entity.dto.newSeller_User;
import com.uade.tpo.E_Commerce.repository.Basic_UserRepository;
import com.uade.tpo.E_Commerce.repository.Company_ShopsRepository;
import com.uade.tpo.E_Commerce.repository.Seller_UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Seller_UserService implements Seller_UserServiceImp {

    @Autowired
    private Seller_UserRepository repository;

    @Autowired
    private Basic_UserRepository basic_userRepository;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private Company_ShopsRepository company_shopsRepository;

    @Autowired
    private ShopsService shopsService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<List<Seller_User>> getAll() {
        return repository.findAllUsers();
    }

    @Override
    public Optional<Seller_User> getById(long id) {
        return repository.findByIdUser(id);
    }

    @Override
    @Transactional
    public Optional<Seller_User> createUser(newSeller_User user, String filePath) {
        repository.insertUser(user.getId_user(), user.getCuit(), user.getCompanyName(), user.getDescription(), user.getState(),filePath);
        // Se asume que el seller_user creado se identifica mediante el id_user que se pasa.
        return repository.findByIdUser(user.getId_user());
    }

    @Override
    @Transactional
    public Optional<Seller_User> updateUser(Seller_User user) {
        Optional<Seller_User> exist = repository.findByIdUser(user.getId());
        if (!exist.isPresent()) {
            return Optional.empty();
        }
        repository.updateUser(user.getId(), user.getCuit(), user.getCompany_name(), user.getDescription(), user.getState());
        entityManager.flush();
        entityManager.clear();
        return repository.findByIdUser(user.getId());
    }

    @Override
    @Transactional
    public boolean deleteUser(long id) {
        Optional<Seller_User> exist = repository.findByIdUser(id);
        if (!exist.isPresent()) {
            return false;
        }
        try {

            ArrayList<Company_Shops> company_shops_list = company_shopsRepository.findAllShopsByUser(id);
            for(Company_Shops shop : company_shops_list){
                shopsService.DeleteShop(shop.getId());
            }

            rolesService.removeRoleFromUser(id);
            repository.deleteUser(id);
            basic_userRepository.deleteUser(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
