/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     Communicates with the Database to create an instance of the User Entity
 * </p>
 *
 * <p>
 *     This UserRepository is inheriting the <code>CrudRepository</code> because we just
 *     want to CRUD User Entities
 * </p>
 */
package com.jwt.auth.springsecurityjwt.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByFirstname(String firstname);
    Optional<User> findByLastname(String lastname);
}
