package org.launchcode.qleanquotes.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    //this class will need to have details about the customers injected in
        @Bean
        PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests()
                    .requestMatchers("/payment", "/createquotes", "/profile").authenticated()
                    .anyRequest().permitAll();
                  http.formLogin()
                          .and()
                          .logout()
                          .logoutUrl("/logout")
                          .logoutSuccessUrl("/login");
            return http.build();
        }


}
