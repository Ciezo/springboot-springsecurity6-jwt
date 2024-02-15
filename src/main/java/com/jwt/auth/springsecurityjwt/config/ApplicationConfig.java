/**
 * @author Cloyd Van Secuya
 * <p>
 *     Holds all the application configurations such as Beans
 * </p>
 */
package com.jwt.auth.springsecurityjwt.config;

import com.jwt.auth.springsecurityjwt.user.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist!"));
    }

//    @Bean
//    public UserDetailsService userDetailsServiceUsingEmail() {
//        return email -> userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist!"));
//    }

}
