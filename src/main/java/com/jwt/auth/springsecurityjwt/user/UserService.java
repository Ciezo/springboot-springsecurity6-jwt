package com.jwt.auth.springsecurityjwt.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private long userId;
    private final UserRepository userRepository;


    /**
     * Extracts the user_id, Primary Key, based on username.
     * We extract the said value from the "user_id" column at User entity
     * @param username an existing or registered user with a username in the database
     * @return userId (long)
     */
    public long getUserId(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if(existingUser.isPresent()) {
            userId = existingUser.get().getId();
        }
        return userId;
    }

    /**
     * Get all `UserDetails` based on id (PRIMARY KEY).
     * We can use this method to extract details about our User who is logged-in or
     * currently in session in the application.
     *
     * Furthermore, we only want to allow general information about the User, and so,
     * we return the common details.
     * @param id
     * @return `UserDetails` such as firstname, lastname, username, and email
     */
    public Optional<User> getAllUserDetailsByUserId(long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }
}
