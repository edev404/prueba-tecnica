package tech.edev404.prueba.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.RequiredArgsConstructor;
import tech.edev404.prueba.configuration.security.service.AppUserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {
    
    private final AppUserDetailsService appUserDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
      return appUserDetailsService;
    }
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
      return configuration.getAuthenticationManager();
    }

}
