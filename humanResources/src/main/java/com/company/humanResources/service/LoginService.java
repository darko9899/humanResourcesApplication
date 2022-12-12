package com.company.humanResources.service;

import com.company.humanResources.exception.ValidationCredentialsExeption;
import com.company.humanResources.model.User;
import com.company.humanResources.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final UserRepo userRepo;

    public LoginService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User login(String username, String password) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPwd().equals(password)) {
                return user;
            }
        }
        return null;
//        throw new ValidationCredentialsExeption("Невалидно потребителско име или парола.");
    }


}

