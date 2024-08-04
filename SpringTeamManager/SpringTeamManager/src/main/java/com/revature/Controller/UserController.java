package com.revature.Controller;

import com.revature.Model.User;
import com.revature.Model.Team;
import com.revature.Model.Owner;
import com.revature.Repos.UserRepository;
import com.revature.Repos.TeamsRepo;
import com.revature.Repos.OwnerRepository;
import com.revature.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@CrossOrigin
public class UserController {
    UserService userService;
    UserRepository userRepository;
    TeamsRepo teamsRepository;
    OwnerRepository ownerRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, TeamsRepo teamsRepository, OwnerRepository ownerRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.teamsRepository = teamsRepository;
        this.ownerRepository = ownerRepository;
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

    @GetMapping(value = "/user/{userId}/teams", produces ="application/json")
    public ResponseEntity<Object> getItemsAssociatedWithUser(@PathVariable int userId) {
        Map<String, String> returnedMessage = new HashMap<String, String>();
        Optional<User> isUserInDB = Optional.ofNullable(userRepository.findByUserId(userId));
        List<Owner> teamsOwnedByUser = ownerRepository.findAllByUserId(userId);
        List<Team> itemsAssociatedWithUser = new ArrayList<>();

        if (isUserInDB.isEmpty()) {
            returnedMessage.put("Error", "User does not exist in the database (incorrect user id)!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnedMessage);
        } else if (teamsOwnedByUser.isEmpty()) {
            returnedMessage.put("Message", "User does not own any teams!");
            return ResponseEntity.status(HttpStatus.OK).body(returnedMessage);
        } else {
            for (Owner owner: teamsOwnedByUser) {
                Team currentTeam = teamsRepository.findByTeamId(owner.getTeam_id());
                itemsAssociatedWithUser.add(currentTeam);
            }
            return ResponseEntity.status(HttpStatus.OK).header("content-type", "application/json").body(itemsAssociatedWithUser);
        }
    }
}



