package com.pk.userservice.userservice.controller;

import com.pk.userservice.userservice.dto.LoginRequestDto;
import com.pk.userservice.userservice.dto.LogoputRequestDto;
import com.pk.userservice.userservice.dto.SignupRequestDto;
import com.pk.userservice.userservice.model.Token;
import com.pk.userservice.userservice.model.Users;
import com.pk.userservice.userservice.service.UserService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto request){
       return userService.login(request.getEmail(),request.getPassword());

    }
    @PostMapping("/signup")
    public Users signUp(@RequestBody SignupRequestDto signUpDto){
        String email=signUpDto.getEmail();
        String name=signUpDto.getName();
        String password=signUpDto.getPassword();
        return userService.signUp(name,email,password);
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoputRequestDto request)
    {
        userService.logout(request.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/validate/{token}")
    public Users validateToken(@PathVariable("token") @NotNull  String token){
        return userService.validateToken(token);
    }
}
