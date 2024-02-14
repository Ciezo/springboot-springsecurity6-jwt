/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     This class is used to extract the User credentials based on JWT (token)
 * </p>
 *
 * <h5>Claims</h5>
 * <p>
 *     <b>Claims</b> are part of the JSON Web Tokens (JWTs).
 *     These are bits of information about a certain subject, say, "name" of a person.
 *     Furthermore, Claims help us gather information about a User or an object
 * </p>
 * <p>
 *     <u>More examples of Claims:</u>
 *     <ol>
 *         <li>sub</li>
 *         <li>iat</li>
 *         <li>role</li>
 *     </ol>
 * </p>
 *
 * <h5>Registered Claims:</h5>
 * <p>
 *     <ol>
 *         <li>iss (issuer)</li>
 *         <li>sub (subject)</li>
 *         <li>aud (audience)</li>
 *         <li>exp (expiration time)</li>
 *         <li>nbf (not before time)</li>
 *         <li>iat (issued at time)</li>
 *         <li>jti (JWT ID)</li>
 *     </ol>
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
