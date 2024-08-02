package com.revature.Controller;

import com.revature.Model.User;
import com.revature.Repos.UserRepository;
import com.revature.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {
    UserService userService;
    UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String hello() {
        return "Hello!";
    }

    @PostMapping(value = "/user/signup", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        Map<String, String> errorMessage = new HashMap<String, String>();
        Optional<User> isUserInDB = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));

        if (user.getUsername().isEmpty()) { // username is empty
            errorMessage.put("Error", "Username is empty!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } else if (user.getPassword().isEmpty()) { // password is empty
            errorMessage.put("Error", "Password is empty!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } else if (user.getUsername().length() < 4) { // username is less than 4 characters
            errorMessage.put("Error", "Username cannot be less than 4 characters long!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } else if (user.getPassword().length() < 4) { // password is less than 4 characters
            errorMessage.put("Error", "Password cannot be less than 4 characters long!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } else if (isUserInDB.isPresent()) {
            errorMessage.put("Error", "Username already exists in the database!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } else {
            User createdUserAccount = userService.createUserAccount(user);
            return ResponseEntity.status(HttpStatus.OK).header("content-type", "application/json").body(createdUserAccount);
        }
    }

    @PostMapping(value = "/user/login", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Object> userLogin(@RequestBody User user) {
        Map<String, String> errorMessage = new HashMap<String, String>();
        Optional<User> isUserInDB = Optional.ofNullable(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()));

        if (user.getUsername().isEmpty()) { // username is empty
            errorMessage.put("Error", "Username is empty!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } else if (user.getPassword().isEmpty()) { // password is empty
            errorMessage.put("Error", "Password is empty!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }  else if (isUserInDB.isEmpty()) {
            errorMessage.put("Error", "User credentials are invalid!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } else {
            User userLoggedIn = userService.userLogin(user);
            return ResponseEntity.status(HttpStatus.OK).header("content-type", "application/json").body(userLoggedIn);
        }
    }
}



