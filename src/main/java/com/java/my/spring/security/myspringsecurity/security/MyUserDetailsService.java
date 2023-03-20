package com.java.my.spring.security.myspringsecurity.security;

import com.java.my.spring.security.myspringsecurity.dao.DatabaseRepo;
import com.java.my.spring.security.myspringsecurity.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final DatabaseRepo repo;

    public MyUserDetailsService(DatabaseRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Unauthorized");
        return new MyUserDetails(user);
    }
}