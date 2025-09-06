package com.example.bookmyshowcb.controllers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDTO {
    public String email;
    public String password;
}
