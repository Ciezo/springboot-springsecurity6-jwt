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
 *
 * <p>
 *     <code>public <T> ...[methood]</code>
 *     This is a generic type of method.
 * </p>
 *
 * <p>
 *     Example: public static void nameMethod(BinaryTreeNode<?> t)
 * </p>
 */
package com.jwt.auth.springsecurityjwt.service;

import com.jwt.auth.springsecurityjwt.keys.AESKeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private AESKeyGenerator aesKeyGenerator = new AESKeyGenerator(256);
    private final String SECRET_SECRET_KEY = aesKeyGenerator.getSecretKey();
    private String username;
    private String email;

    /**
     * username can be used to identify our logged-on user
     * @param token based on the header of the given request from user.
     * @return the "username" from the payload
     */
    public String extractUsername(String token) {
        username = extractSingleClaim(token, Claims::getSubject);           // Subject here is "username"
        return username;
    }

    /**
     * email can be used to identify our logged-on user
     * @param token based on the header of the given request from user.
     * @return the "email" from the payload
     */
    public String extractEmail(String token) {
        email = extractSingleClaim(token, Claims::getSubject);             // Subject here is "email"
        return email;
    }

    /**
     * Use this method to create a token for the logged-in user who has three days until session expiration
     * @param extraClaims which are the sub, iat, exp
     * @param userDetails <code>username</code> or <code>email</code> to identify the logged-on user
     * @return generated token for the Signing-in user
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 72))       // three days expiration
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     *
     * @param token to be resolved
     * @param resolvedClaims
     * @return The resolved single claim based on the token from the payload.
     * @param <T> generic type
     */
    public <T> T extractSingleClaim(String token, Function<Claims, T> resolvedClaims) {
        /* Extract all claims from the token, and then return the resolved claims */
        final Claims claims = extractAllClaims(token);
        return resolvedClaims.apply(claims);
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
