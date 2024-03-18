/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     An endpoint to register an account, or authenticate an existing User.
 * </p>
 *
 */
package com.jwt.auth.springsecurityjwt.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    /**
     * Registers a Single User or Multiple Users
     * @param request coming from a user or multiple users
     * @return store user in the database with a token
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    ) {
            return ResponseEntity.ok(authService.register(request));
    }

    /**
     * Authenticate Existing User from the database
     * @param request using username and password as credentials
     * @return authenticated user with a token
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
