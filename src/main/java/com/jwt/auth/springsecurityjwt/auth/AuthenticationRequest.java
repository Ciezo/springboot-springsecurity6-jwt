/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     This represents what the User can input to be authenticated.
 * </p>
 *
 * <p>
 *     If, the User is existing and has valid credentials then, they are authenticated.
 *     Otherwise, they are not.
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
public class AuthenticationRequest {

    private String username;
    private String password;

}
