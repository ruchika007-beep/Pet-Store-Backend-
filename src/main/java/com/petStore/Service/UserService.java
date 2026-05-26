package com.petStore.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petStore.Entity.User;
import com.petStore.Repository.UserRepository;



@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // Register User
    public User register(User user) {

        return repository.save(user);
    }

    // Login User
    public User login(String email, String password) {

        Optional<User> optionalUser =
                repository.findByEmail(email);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {

                return user;
            }
        }

        return null;
    }
}