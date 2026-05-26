package com.petStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.petStore.Entity.User;
import com.petStore.Service.UserService;



@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService service;

    // =========================
    // SIGNUP
    // =========================
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {

        service.register(user);

        return "User Registered Successfully";
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
    public Object login(@RequestBody User user) {

        User validUser =
                service.login(
                        user.getEmail(),
                        user.getPassword()
                );

        if (validUser != null) {

            return validUser;
        }

        return "Invalid Email or Password";
    }
}
