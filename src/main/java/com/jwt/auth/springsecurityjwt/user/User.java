/**
 * @author Cloyd Van Secuya
 *
 * <h5>Builder pattern</h5>
 * <p>
 *     The <b>builder pattern </b> is a design pattern designed to provide a flexible solution
 *     to various object creation problems in object-oriented programming.
 * </p>
 * <p>
 *     The Builder pattern is a well-known pattern in Java world.
 *     It’s especially useful when you need to create an object with lots of possible configuration options.
 * </p>
 * <p>
 *     <i>Reference:
 *     <ol>
 *         https://en.wikipedia.org/wiki/Builder_pattern
 *         https://refactoring.guru/design-patterns/builder/java/example
 *     </ol>
 *     </i>
 * </p>
 *
 * <h5>User Details</h5>
 * <p>It provides core user information.</p>
 * <p>It helps us understand how the User can interact with the application with a configured security</p>
 *
 *
 *     <b>Some important notes: </b>
 *     <br>
 *     <ol>
 *         <li>A user can have an expiring account</li>
 *         <li>We can lock or unlock the account of user</li>
 *         <li>We can work and configure with user credentials</li>
 *     </ol>
 *
 * <h5>SimpleGrantedAuthority</h5>
 * <p>Store STRING value of a granted authority based on given role</p>
 *
 *
 * //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 * <p>
 *     About this User class. It is written and defined with the common User attributes because
 *     it is designed and required this way, and so, we can just use UserDetails to provide the
 *     actions or configurations required for the User.
 * </p>
 *
 *
 * @// TODO: 2/14/2024
 * <ol>
 *     <li>Create a User</li>
 * </ol>
 */
package com.jwt.auth.springsecurityjwt.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data                                   // Generates getters and getters. Less boilerplate
@Builder                                // Create objects using the Builder pattern
@NoArgsConstructor                      // Same as public User() {}
@AllArgsConstructor                     // Parameterized constructors based on the attributes.
@Entity                                 // Make it as an Entity. Remember for an Entity (table) it need PK or ID
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)         // .TABLE is for MySQL
    private long id;
    private String firstname;
    private String lastname;
    /* The User can either use their given username or email as log-in credentials before password */
    /* User.getUsername() or User.getEmail() */
    private String username; private String email;
    private String password;

    @Enumerated(EnumType.STRING)                             // Tells Spring that Role class is an enum
    private Role role;

    /**
     * The given roles are the following:
     * <ol>
     *     <li>USER</li>
     *     <li>ADMIN</li>
     * </ol>
     * @return USER or ADMIN
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /* Store and return granted authority based on the given role from Role */
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        /* Needs to be true otherwise our users won't be able to connect */
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
