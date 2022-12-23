package com.ruslanburduzhan.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource; // хранится информация для подключения к БД

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        Указываем, что информацию о юзерах нужно брать из БД
        auth.jdbcAuthentication().dataSource(dataSource);


/*      // Данные о юзерах хранятся в "памяти"
        UserBuilder users = User.withDefaultPasswordEncoder(); // дефолтное шифрование паролей
        auth.inMemoryAuthentication()
                .withUser(users
                        .username("ruslan")
                        .password("ruslan")
                        .roles("EMPLOYEE"));
        auth.inMemoryAuthentication()
                .withUser(users
                        .username("elena")
                        .password("elena")
                        .roles("HR"));
        auth.inMemoryAuthentication()
                .withUser(users
                        .username("ivan")
                        .password("ivan")
                        .roles("HR", "MANAGER"));*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .antMatchers("/hr_info").hasAnyRole("HR")
                .antMatchers("/manager_info/**").hasAnyRole("MANAGER")
                .and().formLogin().permitAll();

//        formLogin().permitAll() - форма логина и пароля будет запрашиваться у всех
    }
}
