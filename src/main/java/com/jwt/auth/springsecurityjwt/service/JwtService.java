/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     This class is used to extract the User credentials based on JWT (token)
 * </p>
 */
package com.jwt.auth.springsecurityjwt.service;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private String username;
    private String email;

    public String extractUsername(String token) {
        return username;
    }

    public String extractEmail(String token) {
        return email;
    }

}
