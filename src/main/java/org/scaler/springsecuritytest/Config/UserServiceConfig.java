package org.scaler.springsecuritytest.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserServiceConfig {

    @Bean

    public BCryptPasswordEncoder getencoder(){
        return  new BCryptPasswordEncoder();
    }
}
