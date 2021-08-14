package com.poype.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        System.out.println("UserDetailsServiceImpl");

        if (s.equals("poype")) {
            // 只模拟一个用户 poype
            UserDetails userDetails = new TestUserDetails();
            System.out.println("user password: " + userDetails.getPassword());
            return userDetails;
        }

        throw new UsernameNotFoundException(s + "not exist");
    }
}



class TestUserDetails implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        // 模拟两个权限
        GrantedAuthority authority1 = new TestAuthority("one");
        GrantedAuthority authority2 = new TestAuthority("two");

        grantedAuthorityList.add(authority1);
        grantedAuthorityList.add(authority2);

        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode("abc123");
    }

    @Override
    public String getUsername() {
        return "poype";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


class TestAuthority implements GrantedAuthority {

    private String authority;

    public TestAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return null;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
