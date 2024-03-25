package com.pk.userservice.userservice.service;

import com.pk.userservice.userservice.exception.CustomTokenException;
import com.pk.userservice.userservice.exception.LoginException;
import com.pk.userservice.userservice.model.Token;
import com.pk.userservice.userservice.model.Users;
import com.pk.userservice.userservice.repository.TokenRepository;
import com.pk.userservice.userservice.repository.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepo;

    @Autowired
    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepo) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepo = tokenRepo;
    }

    public Users signUp(String name, String email, String password) {
        Users u = new Users();
        u.setEmail(email);
        u.setName(name);
        u.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepo.save(u);
    }

    public Token login(String email, String password) {
        Optional<Users> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new LoginException("USER DOES NOT EXIST ", "USER_DOES_NOT_EXIST_EXCEPTION");
        }
        Users user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            throw new LoginException("PASSWORD NOT MATCHING EXCEPTION", "PASSWORD_NOT_MATCHING_EXCEPTION");
        }
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(30);
        Date date = Date.from(futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Token token = new Token();
        token.setExpiryAt(date);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        token.setUser(user);
        return tokenRepo.save(token);


    }
    public void logout(String token){
        Optional<Token> token1=tokenRepo.findByValueAndDeleted(token,false);
        if(token1.isEmpty()){
            throw new  CustomTokenException("Token is Deleted","404");
        }
        Token token2=token1.get();
        token2.setDeleted(true);
        tokenRepo.save(token2);

    }
    public Users validateToken(String token){
        Optional<Token> tkn=tokenRepo.findByValueAndDeletedAndExpiryAtGreaterThan(token,false,new Date());

        return tkn.map(Token::getUser).orElse(null);
    }

}
