package org.launchcode.qleanquotes.config;


import org.launchcode.qleanquotes.services.CustomerDetailsServiceImpl;
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


    //this class will need to have details about the customers injected in
    //I thiiiink this is happening in below Bean...
    @Autowired
    private CustomerDetailsServiceImpl customerDetailsService;

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


    //this bit is the meat of SPring Security. this part decides the customized functionality of our security. We want OUR DAO, we want certain pages unrestricted and others not. we want our custom pages.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/authentication/**", "/css/**")
                .permitAll()
                //below, any request beyond the request matchers needs authentication. this is probz why register isnt working.
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/authentication/login")
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




//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authenticationProvider(authenticationProvider())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/authentication/**", "/css/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(formLogin -> formLogin
//                        .usernameParameter("email")
//                        .passwordParameter("password")
//                        .loginPage("/authentication/login")
//                        .failureUrl("/authentication/login?failed")
//                        .loginProcessingUrl("/authentication/login/process")
//                )
//                .logout(logout -> logout
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .logoutUrl("/authentication/logout")
//                        .logoutSuccessUrl("/authentication/login")
//                );
//        return http.build();
//    }





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