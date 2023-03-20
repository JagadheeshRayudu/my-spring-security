package com.java.my.spring.security.myspringsecurity;

import com.java.my.spring.security.myspringsecurity.config.MyConfigProperties;
import com.java.my.spring.security.myspringsecurity.dao.DatabaseRepo;
import com.java.my.spring.security.myspringsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class MySpringSecurityApplication {
    @Autowired
    DatabaseRepo repo;
    @Autowired
    MyConfigProperties myConfigProperties;

    public static void main(String[] args) {
        SpringApplication.run(MySpringSecurityApplication.class, args);
    }

    @PostConstruct
    public void saveUsersToDatabaseOnStartUp() throws Exception {
        try {
            List<User> users = new ArrayList<>();
            users.add(new User(1, "Jagadheesh", "Jagadheesh"));
            users.add(new User(2, "Vijaya", "Vijaya"));
            users.add(new User(3, "ABC", "ABC"));
            users.add(new User(4, "Rayudu", "Rayudu"));
            users.add(new User(5, "Ammulu", "Ammulu"));
            repo.saveAll(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) ->
        {
            System.out.println(myConfigProperties.getId());
            System.out.println(myConfigProperties.getName());
            System.out.println(myConfigProperties.getAge());
        };
    }

    @GetMapping({"/api/{name}", "/api/"})
    public ResponseEntity<?> getData(
            @PathVariable(value = "name", required = true) @NotNull @NotBlank String name,
            @RequestHeader(value = "system", required = true) @NotBlank(message = "Requesting-System cant be blank") String systemName) {
        return ResponseEntity.ok().body(repo.findByUsername(name));
    }
}