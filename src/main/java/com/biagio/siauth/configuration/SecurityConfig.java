package com.biagio.siauth.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProvider authProvider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authenticationProvider(authProvider)
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/registration")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and().csrf().disable();

    }
}
