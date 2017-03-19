package com.pliesveld.discgolf.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pliesveld.discgolf.security.CurrentUser;
import com.pliesveld.discgolf.security.domain.DiscUser;

@RestController
public class UserController {
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ResponseEntity<DiscUser> getAuthenticatedUser(@CurrentUser DiscUser discUser) {
        return ResponseEntity.ok(discUser);
    }
}
