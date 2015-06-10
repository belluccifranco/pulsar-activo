package com.pulsaractivo.controller;

import com.pulsaractivo.model.UserAccount;
import com.pulsaractivo.service.UserAccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
public class LoginController {

    private final UserAccountService userAccountService;

    @Autowired
    public LoginController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserAccount userAccount) throws ServletException {
        if (!userAccountService.validateUserAccount(userAccount)) {
            throw new ServletException("Invalid login");
        }

        String token = Jwts.builder()
                .setSubject(userAccount.getUsername())
                //.claim("roles", userAccount.getUserRoles().toString())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "pulsaractivo")
                .compact();

        return new LoginResponse(token);
    }

    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}
