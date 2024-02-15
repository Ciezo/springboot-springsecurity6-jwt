/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     The JWT Authentication Filter which is active for every request
 *     sent by the user.
 * </p>
 *
 * <p>
 *     <code>@Service</code> is used here because here I want to separate it
 *     from the rest of the application, and make it usable onto other parts
 *     such as the presentation layer (Controller) or persistence layer (Repository).
 * </p>
 */
package com.jwt.auth.springsecurityjwt.config;

import com.jwt.auth.springsecurityjwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * @note The parameters should not be empty!
     * @param request User request for authentication
     * @param response Response body returned by the Server
     * @param filterChain JWT Auth Filter, a design by Spring Security
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        /* The header on User request should have an Auth token */
        final String authHeader = request.getHeader("Authorization");      // We try to extract that from the header
        if(authHeader == null || authHeader.isBlank() || authHeader.isEmpty()
            && !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            /* Stop the execution of security filter chain here */ return;
        }

        /* The JWT and JWT Service is initiated here */
        /* username and email are extracted from JWT token */
        final String username; final String email;
        final String jwt;
        /**
         * Extracting the jwt
         */
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        email = jwtService.extractEmail(jwt);

        /** Validate if user is connected and authenticated in the application
         * .getAuthentication() == null, when user is not authenticated or connected yet */
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Get the user from the database, and check if the user exists in the db
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // fetch user using "username"
        }

//        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             UserDetails userDetails = this.userDetailsService.loadUserByEmail(email); // fetch user using "email"
//         /**
//         * @// TODO: 2/15/2024
//         * - implement loading the User by Email
//         */
//        }
    }
}
