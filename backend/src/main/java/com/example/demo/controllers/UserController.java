package com.example.demo.controllers;

import com.example.demo.services.CustomUserDetailsService;
import com.example.demo.utils.LoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders =  "*")
@RequestMapping("/users")
public class UserController {
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/login")
    public ResponseEntity loginIn(){
        try{
            String loggedUser = LoggedUser.getLoggedUser();
            return ResponseEntity.status(HttpStatus.OK).body(customUserDetailsService.loadUserByUsername(loggedUser));
        }
        catch(IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception);
        }
    }

}
