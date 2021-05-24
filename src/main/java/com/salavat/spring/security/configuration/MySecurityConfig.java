package com.salavat.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);

//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("salavat").password("salavat").roles("developer"))
//                .withUser(userBuilder.username("elena").password("elena").roles("HR"))
//                .withUser(userBuilder.username("ivan").password("ivan").roles("HR", "MANAGER"));

//        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("superadmin").password("superadmin").roles("SUPERADMIN");
   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("HR", "MANAGER", "developer")
                .antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info").hasRole("MANAGER")
                .and().formLogin().permitAll();

//        http.authorizeRequests()
//                .antMatchers("/hr_info/**").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/manager_info/**").access("hasRole('ROLE_SUPERADMIN')")
//                .and().formLogin().defaultSuccessUrl("/", true);

    }
}
