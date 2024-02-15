/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     Binding and using the JWTs Service and configuration of security in the application
 * </p>
 */
package com.jwt.auth.springsecurityjwt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * @// TODO: 2/15/2024
     * - add configuring all the HTTP security for requests from the Client
     */

}
