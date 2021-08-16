package com.example.adminpanel.service;

import com.example.adminpanel.repository.AdminUserRepository;
import com.example.commanentity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
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
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<Admin> user = adminUserRepository.findByEmailID(username);
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("AdminUser not found with username: " + username);
        }
        return new User(user.get(0).getEmailID(), user.get(0).getPassword(),
                grantedAuthorities);
    }

    public UserDetails loadUserByUsername(String username, String role) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<Admin> user = adminUserRepository.findByEmailID(username);
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("AdminUser not found with username: " + username);
        }
        return new User(user.get(0).getEmailID(), user.get(0).getPassword(),
                grantedAuthorities);
    }
}
