/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     This Class has a tightly-coupled relationship with the <code>AuthenticationController</code>
 * </p>
 *
 * <p>
 *     This Class delegates how the <b>registration</b> and <b>authentication</b> of Users.
 * </p>
 */
package com.jwt.auth.springsecurityjwt.auth;

import com.jwt.auth.springsecurityjwt.service.JwtService;
import com.jwt.auth.springsecurityjwt.user.User;
import com.jwt.auth.springsecurityjwt.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder pwdEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    /**
     * Register and create a User to the database using
     * the following attributes: firstname, lastname, email, password
     * @param request to create a User object
     * @return registered User with a session and token
     */
    public AuthenticationResponse register(RegisterRequest request) {
        /* Create the User Object */
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(pwdEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        /* Save user to database */
        var savedUserInDb = userRepository.save(user);
        /* Generate a token for the user */
        var userJwtToken = jwtService.generateToken(savedUserInDb);

        return AuthenticationResponse.builder()
                .token(userJwtToken)
                .build();
    }

    /**
     * Create and Register a batch of Users using multiple objects of RegisterRequest
     * @param requests Lists of RegisterRequest objects
     * @return registered User with a session and token
     */
    public List<AuthenticationResponse> registerBatch(List<RegisterRequest> requests) {
        List<AuthenticationResponse> responses = new ArrayList<>();

        for (RegisterRequest request : requests) {
            /* Create the User Object */
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(pwdEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            /* Save user to database */
            var savedUserInDb = userRepository.save(user);
            /* Generate a token for the user */
            var userJwtToken = jwtService.generateToken(savedUserInDb);
            /* Create a token for each user */
            AuthenticationResponse response = AuthenticationResponse.builder()
                    .token(userJwtToken)
                    .build();
            /* Add to the list of authentication responses */
            responses.add(response);
        }
        
        return responses;
    }

    /**
     * Authenticate the existing User
     * @param request
     * @return
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        /* Authenticate using username and password as credentials 1*/
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        ); // Once, this is finished executing the User is authenticated (logged-in)

        /**
         * Create a token for the Authenticated User
         */
        var user = userRepository.findByUsername(request.getUsername())
                        .orElseThrow();

        var authenticatedUserJwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(authenticatedUserJwtToken)
                .build();
    }

}
