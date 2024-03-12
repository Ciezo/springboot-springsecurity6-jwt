/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     An endpoint to register an account, or authenticate an existing User.
 * </p>
 *
 */
package com.jwt.auth.springsecurityjwt.auth;

import com.jwt.auth.springsecurityjwt.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
        try {
            /**
             * For Debugging requests
             * //////////////////////////////////////////////////////////////////////// */
            /* Uncomment both prints to debug the request values and types */
            // System.out.println("Class Type: " + request.getClass().getName());
            // System.out.println("Request: " + request.toString());
            /** * //////////////////////////////////////////////////////////////////// */

            if (request instanceof LinkedHashMap<?,?>) {
                RegisterRequest singleRequest =
                        new RegisterRequest(
                                (String) ((LinkedHashMap<?, ?>) request).get("firstname"),
                                (String) ((LinkedHashMap<?, ?>) request).get("lastname"),
                                (String) ((LinkedHashMap<?, ?>) request).get("username"),
                                (String) ((LinkedHashMap<?, ?>) request).get("email"),
                                (String) ((LinkedHashMap<?, ?>) request).get("password"),
                                (Role) ((LinkedHashMap<?, ?>) request).get("role")
                        );
                return ResponseEntity.ok(
                        authService.register(singleRequest)
                );
            } else if (request instanceof ArrayList<?>) {
                List<RegisterRequest> batchRequests = (List<RegisterRequest>) request;
                return ResponseEntity.ok(
                        authService.registerBatch(batchRequests)
                );
            } else {
                return ResponseEntity
                        .badRequest()
                        .body("Invalid request format");
            }

        } catch(Exception err) {
            System.out.println("An error occurred in deserialization of request");
            System.out.println("Error: " + err);
            throw err;
        }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
