/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.springchat;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import demo.springchat.entity.Account;
import demo.springchat.repo.AccountRepo;

/**
 * @author nathaniel.a.camomot
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //See https://jira.springsource.org/browse/SPR-11496
                .headers().addHeaderWriter(
                        new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)).and()
        .authorizeRequests()
                .antMatchers("/webjars/**", "/css/**", "/js/**", "/img/**", "/register", 
                        "/echo_jsr.html", "/echo_spring.html", "/lubchanco.html", 
                        "/jsrecho", "/springecho",  "/springecho/**").permitAll()
                .anyRequest().authenticated()
                .and()
        .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
        .logout()
                .permitAll();
    }

    @Autowired
    private UserDetailsService userDetailsServiceImpl() {
        return (String username) -> {
            Account account = accountRepo.findByUsername(username);
            if (account != null) {
                return new User(account.getUsername(), account.getPassword(),
                        toAuthorities(account.getRoles()));
            } else {
                throw new UsernameNotFoundException("could not find the user '"
                        + username + "'");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    private Set<GrantedAuthority> toAuthorities(Set<String> roles) {
        return roles.stream().map((String t) -> new SimpleGrantedAuthority(t)).collect(Collectors.toSet());
    }

}
