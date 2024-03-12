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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
    public ResponseEntity<?> register (
            @RequestBody Object request
    ) {

        if (request instanceof RegisterRequest) {
            RegisterRequest singleRequest = (RegisterRequest) request;
            return ResponseEntity.ok(
                    authService.register(singleRequest)
            );
        } else if (request instanceof List) {
            List<RegisterRequest> batchRequests = (List<RegisterRequest>) request;
            return ResponseEntity.ok(
                    authService.registerBatch(batchRequests)
            );
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid request format");
        }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
