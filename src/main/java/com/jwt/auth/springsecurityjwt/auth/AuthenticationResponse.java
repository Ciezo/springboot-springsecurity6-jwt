/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     This Class is responsible for returning to the user for their token.
 *     Furthermore, this helps us to understand how a user can be notified
 *     if they are logged-in or logged-out.
 *
 *     <b>The token can expire.</b>
 *     <b>The token can refresh.</b>
 * </p>
 */
package com.jwt.auth.springsecurityjwt.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
}
