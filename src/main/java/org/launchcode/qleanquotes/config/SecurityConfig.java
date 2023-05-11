package org.launchcode.qleanquotes.config;


import org.launchcode.qleanquotes.services.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    //this class will need to have details about the customers injected in
    //I thiiiink this is happening in below Bean...
    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/authentication/**", "/css/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/authentication/login")
                .permitAll()
                .failureUrl("/authentication/login?failed")
                .usernameParameter("email")
                .passwordParameter("password")
//                .loginProcessingUrl("/authentication/login/process")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/authentication/login") .permitAll();

//        http.authenticationProvider(authenticationProvider());
//        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

}


//tutorial 2
//        .authorizeHttpRequests()
//        .requestMatchers("/payment", "/createquotes", "/profile").authenticated()
//        .anyRequest()
//        .permitAll().and()
//        http.formLogin()
////                          .loginPage("/authentication/login")
////                          .loginProcessingUrl("/authentication/login")
//        .and()
//        .logout()
//        .logoutUrl("/logout");
////                          .logoutSuccessUrl("/login");


//tutorial 3?
//formLogin()
//                .loginPage("/authentication/login")
//                .loginProcessingUrl("/authentication/login/process")
//                .defaultSuccessUrl("/index", true)
//                .failureUrl("/authentication/login?failed")
//                .and()
//                .logout()
//                .logoutUrl("/authentication/logout")
//                .logoutSuccessUrl("/authentication/login")


    //below creates temporary custom users
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.withUsername("user1")
//                .password(passwordEncoder().encode("user1Pass"))
//                .roles("USER")
//                .build();
//        UserDetails user2 = User.withUsername("user2")
//                .password(passwordEncoder().encode("user2Pass"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder().encode("adminPass"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2, admin);
//    }