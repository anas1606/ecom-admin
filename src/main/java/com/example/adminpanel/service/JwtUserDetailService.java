package com.example.adminpanel.service;

import com.example.adminpanel.repository.AdminUserRepository;
import com.example.commanentity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Admin> user = adminUserRepository.findByEmailid(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("AdminUser not found with username: " + username);
        }
        return new User(user.get(0).getEmailid(), user.get(0).getPassword(),
                getAuthority());
    }

    private Set<SimpleGrantedAuthority> getAuthority() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }
}
