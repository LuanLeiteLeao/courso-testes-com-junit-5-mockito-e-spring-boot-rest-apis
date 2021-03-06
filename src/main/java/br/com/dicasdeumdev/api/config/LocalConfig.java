package br.com.dicasdeumdev.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
    
    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){

        User u1 = new User(null,"Luiza","luiza@gmail.com","123");
        User u2 = new User(null,"Marcos","marcos@gmail.com","123");
        User u3 = new User(null,"Jefesson","Jefesson@gmail.com","123");

        repository.saveAll(List.of(u1,u2,u3));
        
    }
}
