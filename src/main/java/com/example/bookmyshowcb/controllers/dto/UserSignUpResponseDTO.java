package com.example.bookmyshowcb.controllers.dto;

import com.example.bookmyshowcb.controllers.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpResponseDTO {
    private Long UserId;
    private ResponseStatus ResponseStatus;
    private String ResponseMessage;
}
