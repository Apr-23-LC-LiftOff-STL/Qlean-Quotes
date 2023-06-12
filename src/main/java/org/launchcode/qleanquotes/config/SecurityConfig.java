package org.launchcode.qleanquotes.config;


import org.launchcode.qleanquotes.services.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //below creates our "authenticator", wires in user details and our encoder. creates authProvider object
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customerDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //this bit is the meat of Spring Security. this part decides the customized functionality of our security. We want OUR DAO, we want certain pages unrestricted and others not. we want our custom pages.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/authentication/**", "/css/**", "/images/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/authentication/login")

                .loginPage("/authentication/register")
                .failureUrl("/authentication/login?failed")
                .loginProcessingUrl("/authentication/login/process")
                .and()
                .logout()
                .logoutUrl("/authentication/logout")
                .logoutSuccessUrl("/authentication/login")
                .invalidateHttpSession(true)
                .clearAuthentication(true);
    //sets our DAOAuthProvider as the auth provider used by spring security
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}


