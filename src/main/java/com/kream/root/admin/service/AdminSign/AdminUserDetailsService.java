package com.kream.root.admin.service.AdminSign;

import com.kream.root.admin.domain.Admin;
import com.kream.root.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Admin admin = adminRepository.findByUsername(username);
        Long id = Long.parseLong(username);
        Admin admin = adminRepository.findOne(id);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(admin.getUsername(), admin.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
