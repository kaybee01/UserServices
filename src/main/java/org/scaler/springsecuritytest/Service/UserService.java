package org.scaler.springsecuritytest.Service;

import org.apache.commons.lang3.RandomStringUtils;
import org.scaler.springsecuritytest.Model.Token;
import org.scaler.springsecuritytest.Model.User;
import org.scaler.springsecuritytest.Repository.TokenRepo;
import org.scaler.springsecuritytest.Repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;
    private TokenRepo tokenRepo;

    private BCryptPasswordEncoder encoder;

    UserService(BCryptPasswordEncoder encoder,UserRepo userRepo,TokenRepo tokenRepo){
        this.encoder=encoder;
        this.userRepo=userRepo;
        this.tokenRepo=tokenRepo;
    }

    public User signUp(String name,String email,String password){
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setHashedPassword(encoder.encode(password));
        u.setIs_verified(false);
        return userRepo.save(u);
    }

    public Token loginM(String email,String password){
        Optional<User> optionalUser = userRepo.findByEmail(email);

        if(optionalUser.isEmpty()){
            throw new RuntimeException("Invalid UserName");
        }
        User user = optionalUser.get();

        if(!encoder.matches(password,user.getHashedPassword())){
            throw  new RuntimeException("Invalid Password");
        }
        Token t = generateToken(user);
        t = tokenRepo.save(t);
        return t;
    }

    public Token generateToken(User u){
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysLater = currentDate.plusDays(30);
        Date expiry = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Token token =new Token();
        token.setExpiry(expiry);
        token.setUser(u);
        token.setValid(true);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        return  token;
    }

    public Token logoutM(String token){
        Optional<Token> optionalToken = tokenRepo.findByValue(token);

        if (optionalToken.isEmpty()){
            throw new RuntimeException("Invalid Token");
        }
        Token t =  optionalToken.get();

        t.setValid(false);
        tokenRepo.save(t);
        return t;
    }

    public User validateUser(String t){
      //  Optional<Token> optionalToken = tokenRepo.findByValue(t);

//        LocalDate c = LocalDate.now();
//
//        if (optionalToken.isEmpty() || optionalToken.get().isvalid() == false  ){
//           // throw  new RuntimeException("Invalid Token");
//            return  null;
//        }
//
//        User u = optionalToken.get().getUser();
//        return u;

        Optional<Token> optionalToken = tokenRepo.findByValueAndIsValid(t,true);

        if (optionalToken.isEmpty()){
            throw new RuntimeException("wd");
        }

        User u = optionalToken.get().getUser();
        return u;

    }

}
