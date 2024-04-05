package com.jwt.auth.springsecurityjwt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/inkdown/v1/user")
public class UserController {

    @Autowired
    private UserService service;
    // This endpoint does not require Authentication
    @GetMapping("/{username}")
    public ResponseEntity<Long> getUserIdByUsername(
            @PathVariable("username")
            String username) {
        return new ResponseEntity<>(
                service.getUserId(username),
                HttpStatus.OK
        );
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<Optional<User>> getAllUserDetailsByUserId(
            @PathVariable(value = "userId") long id
    ) {
        return new ResponseEntity<>(
                service.getAllUserDetailsByUserId(id),
                HttpStatus.OK
        );
    }

}
