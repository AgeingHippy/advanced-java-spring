/* CodingNomads (C)2024 */
package com.codingnomads.springsecurity.authorization.addingauthorization.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // disable CSRF to allow Postman to interact with the application
                .csrf(csrf -> csrf.disable())
//                .addFilterAfter(new AuditInterceptor(), AnonymousAuthenticationFilter.class)
                // start changing endpoint authorization requirements
                .authorizeHttpRequests(auth -> auth
                        // the following 4 paths should be allowed to all always. They are static and are required to
                        // present the pages properly.
                        .requestMatchers("/js/**", "/css/**", "/img/**", "/webjars/**").permitAll()
                        // make sure that the admin page can only be accessed user with ROLE_ADMIN
                        .requestMatchers("/admin", "/random/admin").hasRole("ADMIN")
                        // only allow users with ROLE_SUPERU to access the super user page
                        .requestMatchers("/superu", "/random/su").hasRole("SUPERU")
                        // only allow users with an UPDATER authority to update users.
                        .requestMatchers("/update-user").hasAuthority("UPDATER")
                        // make sure that all others requests require authentication.
                        .requestMatchers("users/post-auth").permitAll()
//                        .requestMatchers("users/post-auth").anonymous()
                        .anyRequest().authenticated())
                // use HttpBasic authentication for /update-user, withDefaults() allows you to chain the next method
                .httpBasic(Customizer.withDefaults())
//                .addFilter(new AnonymousAuthenticationFilter("BOB"))
                // use a form to log in with the default login page
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
