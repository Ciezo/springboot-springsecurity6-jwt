package com.jwt.auth.springsecurityjwt.notes;


import com.jwt.auth.springsecurityjwt.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {
    private Notes note;
    private User user;
}
