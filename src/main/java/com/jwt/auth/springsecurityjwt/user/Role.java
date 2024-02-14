/**
 * @author Cloyd Van Secuya
 *
 * <p>We only have two roles.</p>
 * <ol>
 *     <li>User - who can log-in or log-out with no priveleges</li>
 *     <li>Admin - who can view all User accounts and has priveleges.</li>
 * </ol>
 *
 * <p>Priveleges:</p>
 * <ol>
 *     <li>Create User accounts</li>
 *     <li>Delete User accounts</li>
 *     <li>Edit User accounts</li>
 *     <li>View User accounts</li>
 * </ol>
 */
package com.jwt.auth.springsecurityjwt.user;

public enum Role {
    USER,
    ADMIN
}
