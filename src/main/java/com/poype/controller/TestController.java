package com.poype.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("login")
    public String login() {
        return "login page";
    }
}

/**
 * DelegatingFilterProxy： SpringBoot自动配置
 *       FilterChainProxy: 它是SpringSecurity的入口，从名字也能够看出它是一系列SpringSecurityFilter的代理。它下面可以有多个SecurityFilterChain
 *       SecurityFilterChain：it is used by FilterChainProxy to determine which Spring Security Filters should be invoked for this request.
 *          SecurityFilter：一个SecurityFilterChain中可以包含任意数量的SecurityFilter
 */



// org.springframework.security.web.DefaultSecurityFilterChain
//