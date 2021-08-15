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
        http.formLogin()
                .loginPage("/login.html") // 登录页面
                .loginProcessingUrl("/user/login") // 登录接口，该接口SpringSecurity会自动实现，前端直接调用该接口即可
                .defaultSuccessUrl("/test/index")  // 登录成功之后，默认跳转路径
                .and().authorizeRequests()
                .antMatchers("/user/login", "/login.html").permitAll()
                .antMatchers("/test/hello").hasAuthority("admin")
                .anyRequest().authenticated().and().csrf().disable(); // 这里必须要将csrf disable掉，否则无法调用登录接口
    }

}
