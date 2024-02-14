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
 */
package com.jwt.auth.springsecurityjwt.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                                   // Generates getters and getters. Less boilerplate
@Builder                                // Create objects using the Builder pattern
@NoArgsConstructor                      // Same as public User() {}
@AllArgsConstructor                     // Parameterized constructors based on the attributes.
@Entity                                 // Make it as an Entity. Remember for an Entity (table) it need PK or ID
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)         // .TABLE is for MySQL
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
