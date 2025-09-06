package com.example.bookmyshowcb.controllers;

import com.example.bookmyshowcb.controllers.dto.UserLoginRequestDTO;
import com.example.bookmyshowcb.controllers.dto.UserLoginResponseDTO;
import com.example.bookmyshowcb.controllers.dto.UserSignUpRequestDTO;
import com.example.bookmyshowcb.controllers.dto.UserSignUpResponseDTO;
import com.example.bookmyshowcb.controllers.enums.ResponseStatus;
import com.example.bookmyshowcb.models.User;
import com.example.bookmyshowcb.services.UserService;
import com.example.bookmyshowcb.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public UserSignUpResponseDTO signUp(@RequestBody UserSignUpRequestDTO requestDTO){
        UserSignUpResponseDTO responseDTO = new UserSignUpResponseDTO();

        try{
            UserUtils.validatePassword(requestDTO.getPassword());
            User user = userService.signup(requestDTO.getUsername(),requestDTO.getEmail(),requestDTO.getPassword());
            responseDTO.setUserId(user.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setResponseMessage("Sign up successful");
        } catch (Exception e) {
            responseDTO.setUserId(null);
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setResponseMessage(e.getMessage());
        }
        return responseDTO;
    }

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginRequestDTO requestDTO){

        UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();

        try{
            User user = userService.login(requestDTO.getEmail(),requestDTO.getPassword());
            responseDTO.setUserId(user.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setResponseMessage("Sign up successful");
        } catch (Exception e) {
            responseDTO.setUserId(null);
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setResponseMessage(e.getMessage());
        }
        return responseDTO;
    }
}
