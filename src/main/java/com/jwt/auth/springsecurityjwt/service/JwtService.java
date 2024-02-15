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
 *
 *     <b>Claims are part of the payload of the JWTs</b>
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
 *
 * <h5>Signing Key</h5>
 * <p>
 *     A <b>Signing Key</b> is used as a secret value to digitally sign a JWT. This is done to enhance
 *     security and confidence with the parsing token coming from the Authentication headers.
 *
 *     <b>Integrity</b> of the message is very important here as to ensure that it wasn't changed when
 *     it was sent from the client to the server.
 * </p>
 *
 */
package com.jwt.auth.springsecurityjwt.service;

import com.jwt.auth.springsecurityjwt.keys.AESKeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    private AESKeyGenerator aesKeyGenerator = new AESKeyGenerator(256);
    private final String SECRET_SECRET_KEY = aesKeyGenerator.getSecretKey();
    private String username;
    private String email;

    public String extractUsername(String token) {
        return username;
    }

    public String extractEmail(String token) {
        return email;
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
