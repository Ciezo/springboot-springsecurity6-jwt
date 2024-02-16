/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     Attributes used by the customer to register.
 * </p>
 */
package com.jwt.auth.springsecurityjwt.auth;

import com.jwt.auth.springsecurityjwt.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Role role;

}
