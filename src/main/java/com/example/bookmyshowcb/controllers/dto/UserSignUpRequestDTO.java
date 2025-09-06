package com.example.bookmyshowcb.controllers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequestDTO {
    String username;
    String email;
    String password;
}
