package com.example.bookmyshowcb.services;

import com.example.bookmyshowcb.exceptions.PasswordIncorrectException;
import com.example.bookmyshowcb.exceptions.UserAlreadyExistsException;
import com.example.bookmyshowcb.exceptions.UserNotFoundException;
import com.example.bookmyshowcb.models.User;
import com.example.bookmyshowcb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signup(String userName, String email, String password) throws UserAlreadyExistsException {
        // 1. check if email already exists?

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists");
        }


        // 2. Now I am sure user is not there in the DB
        // make a new user and add to the DB
        User user = new User();
        user.setName(userName);
        user.setEmail(email);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User login(String email, String password) throws UserNotFoundException, PasswordIncorrectException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with email " + email + " does not exist");
        }
        User user = optionalUser.get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(password, user.getPassword());
        if(!matches){
            throw new PasswordIncorrectException("Password is incorrect");
        }

        return user;
    }
}
