package com.ecommercevcs.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommercevcs.entities.RoleEntity;
import com.ecommercevcs.entities.UserEntity;
import com.ecommercevcs.entities.embeddable.Address;
import com.ecommercevcs.repositories.RoleRepository;
import com.ecommercevcs.repositories.UserRepository;

@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) {
        
        createAdminUser();
        createUser("Sergio", "Cuadrado", "Corrales", "sergio@sergio.com", "Sergio12345.", "678123456", 
                createAddress("Gran Vía 12", "Salamanca", "Salamanca", "España", "37001"), true);
        createUser("Carlos", "Delgado", "Benito", "carlos@carlos.com", "Carlos12345.", "678234567", 
                createAddress("Calle Toro 15", "Salamanca", "Salamanca", "España", "37002"), true);
        createUser("Pablo", "Vincent", "PB", "pablo@pablo.com", "Pablo12345.", "678345678", 
                createAddress("Plaza Mayor 1", "Salamanca", "Salamanca", "España", "37001"), true);
                
        createUser("Laura", "Martín", "Sánchez", "laura@laura.com", "Laura12345.", "654789123", 
                createAddress("Calle Zamora 42", "Salamanca", "Salamanca", "España", "37002"), false);
        createUser("Manuel", "Herrero", "González", "manuel@manuel.com", "Manuel12345.", "654123789", 
                createAddress("Calle Rúa Mayor 15", "Salamanca", "Salamanca", "España", "37001"), false);
        createUser("Ana", "Pérez", "Jiménez", "ana@ana.com", "Ana12345.", "654321987", 
                createAddress("Avenida de Portugal 122", "Salamanca", "Salamanca", "España", "37003"), false);
    }
    
    private void createAdminUser() {
        if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setName("Admin");
            admin.setFirstName("Admin");
            admin.setLastName("Administrator");
            admin.setEmail("admin@admin.com");
            admin.setPassword(passwordEncoder.encode("Admin12345."));
            admin.setPhoneNumber("666555444");
            admin.setEnabled(true);
            admin.setAddress(createAddress("Calle del Rector Lucena 4", "Salamanca", "Salamanca", "España", "37002"));
            
            List<RoleEntity> roles = new ArrayList<>();
            Optional<RoleEntity> adminRole = roleRepository.findByName("ROLE_ADMIN");
            Optional<RoleEntity> userRole = roleRepository.findByName("ROLE_USER");
            
            if (adminRole.isPresent()) {
                roles.add(adminRole.get());
            }
            if (userRole.isPresent()) {
                roles.add(userRole.get());
            }
            
            admin.setRoles(roles);
            userRepository.save(admin);
        }
    }
    
    private void createUser(String name, String firstName, String lastName, String email, String password, 
                           String phoneNumber, Address address, boolean isAdmin) {
        if (userRepository.findByEmail(email).isEmpty()) {
            UserEntity user = new UserEntity();
            user.setName(name);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setPhoneNumber(phoneNumber);
            user.setEnabled(true);
            user.setAddress(address);
            
            List<RoleEntity> roles = new ArrayList<>();
            Optional<RoleEntity> userRole = roleRepository.findByName("ROLE_USER");
            
            if (userRole.isPresent()) {
                roles.add(userRole.get());
            }
            
           
            if (isAdmin) {
                Optional<RoleEntity> adminRole = roleRepository.findByName("ROLE_ADMIN");
                if (adminRole.isPresent()) {
                    roles.add(adminRole.get());
                }
            }
            
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
    
    private Address createAddress(String street, String city, String state, String country, String postalCode) {
        Address address = new Address();
        address.setStreetAddress(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setPostalCode(postalCode);
        return address;
    }
}