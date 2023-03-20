package com.java.my.spring.security.myspringsecurity.controller;

import com.java.my.spring.security.myspringsecurity.dao.DatabaseRepo;
import com.java.my.spring.security.myspringsecurity.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/")
public class MyController {
    @Autowired
    DatabaseRepo repo;
    @Autowired
    Environment env;

    @GetMapping({"get"})
    public ResponseEntity<String> getData() {
        log.info("Hi User");
        return ResponseEntity.ok().body("Hi User");
    }

    @GetMapping({"getAllUsers"})
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok()
                .body(repo.findAll());
    }

    @GetMapping({"getUser/{id}"})
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(repo.findById(id).orElse(new User(9999999999999l, null, null)));
    }
    @GetMapping("/getPage")
    public String getPage(){
        return "index";
    }
}