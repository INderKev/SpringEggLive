package com.egg.biblioteca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.AntPathMatcher;

import com.egg.biblioteca.servicios.UsuarioServicio;

import jakarta.annotation.security.PermitAll;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
public class SeguridadWeb{

    

    public UsuarioServicio usuarioServicio;

    public SeguridadWeb (UsuarioServicio usuServicio){
        this.usuarioServicio = usuServicio;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioServicio)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/**")
                    .permitAll()               
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .loginProcessingUrl("/logincheck")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl( "/inicio")
                    .permitAll()
            )
            .logout(logout ->
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll()
            )              
        ;

         http.formLogin(withDefaults());
        http.httpBasic(withDefaults());


         return http.build();
    }
    
    /* 
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
             .authorizeHttpRequests((authorizeHttpRequests) ->
                 authorizeHttpRequests
                     .requestMatchers("/admin/**").hasRole("ADMIN")
                     .requestMatchers("/**").hasRole("USER")
             )
             .formLogin(withDefaults());
         return http.build();
    } 

     @Bean
     public UserDetailsService userDetailsService() {
         UserDetails user = User.withDefaultPasswordEncoder()
             .username("user")
             .password("password")
             .roles("USER")
             .build();
         UserDetails admin = User.withDefaultPasswordEncoder()
             .username("admin")
             .password("password")
             .roles("ADMIN", "USER")
             .build();
         return new InMemoryUserDetailsManager(user, admin);
     }
     */
     
    
}
