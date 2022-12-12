package com.company.humanResources.controller;

import com.company.humanResources.model.LoginDataDTO;
import com.company.humanResources.model.User;
import com.company.humanResources.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public User login(@RequestBody final LoginDataDTO loginData) {
        final String username = loginData.getUsername();
        final User user = loginService.login(username, loginData.getPassword());
        return user;
    }
}
