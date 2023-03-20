package com.java.my.spring.security.myspringsecurity.dao;

import com.java.my.spring.security.myspringsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}