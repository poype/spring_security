package com.poype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserDetailSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 这里要调用父类的configure方法，默认条件SpringSecurity会保护所有的URI资源，这个默认设置很合理，
        // 如果某个URI资源不需要被保护可以明确再指定
        super.configure(http);

        /**
         * permitAll方法的作用是允许对应的URI资源不受限制可以被外界任意访问。如果这里的/test/login资源不调用permitAll放行，那么
         * 用户访问login页面的时候仍然会收到302 responseCode继续跳转到login页面，这样就会导致死循环。
         */
        http.formLogin().loginPage("/test/login").permitAll();
    }


}
