/**
 * @author Cloyd Van Secuya
 *
 * <p>
 *     A user can start creating or saving notes with title and body.
 *     And, the author is assigned from the username of the User.
 * </p>
 * <p>
 *     Example:
 *      username =  "cloydvan"
 *      ...therefore, author = username, or author is "cloydvan".
 * </p>
 * <p>
 *     The `body` of Note holds 65,535 bytes which is possible with @Column(columnDefinition="TEXT")
 * </p>
 */
package com.jwt.auth.springsecurityjwt.notes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String title;
    private String author;
    @Column(columnDefinition = "LONGTEXT")
    private String body;
}
