package com.tcontechco.Tourney.security;


import com.tcontechco.Tourney.jwt.JwtConfig;
import com.tcontechco.Tourney.jwt.JwtTokenVerifier;
import com.tcontechco.Tourney.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.tcontechco.Tourney.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final PlayerService playerService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     PlayerService playerService,
                                     SecretKey secretKey,
                                     JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.playerService = playerService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().requestMatchers()
                .antMatchers("/api/**")
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig,secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey,jwtConfig),JwtUsernameAndPasswordAuthenticationFilter.class)

                .authorizeRequests()
                .antMatchers("/api/v1/register").permitAll()
                .anyRequest()
                .authenticated();

        http.cors().configurationSource(configurationSource());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(playerService);
        return provider;
    }

    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token", "authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }





}
