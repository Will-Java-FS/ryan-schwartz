package com.revature.Controller;
import com.revature.Model.Owner;
import com.revature.Model.Team;
import com.revature.Model.User;
import com.revature.Repos.OwnerRepository;
import com.revature.Repos.TeamsRepo;
import com.revature.Repos.UserRepository;
import com.revature.Services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/owner")
@CrossOrigin
public class OwnerController {
    OwnerService os;
    OwnerRepository or;
    UserRepository ur;
    TeamsRepo tr;

    @Autowired
    public OwnerController(OwnerService os, OwnerRepository or, UserRepository ur, TeamsRepo tr) {
        this.os = os;
        this.or = or;
        this.ur = ur;
        this.tr = tr;
    }

    @PostMapping(value = "/add", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Object> addTeam(@RequestBody Owner owner)
    {
        Map<String, String> message = new HashMap<String, String>();
        Optional<User> isUserInDB = Optional.ofNullable(ur.findByUserId(owner.getUser_id()));
        Optional<Team> isTeamInDB = Optional.ofNullable(tr.findByTeamId(owner.getTeam_id()));
        Optional<Owner> userOwnsTeam = Optional.ofNullable(or.findByIds(owner.getUser_id(), owner.getTeam_id()));

        if (isUserInDB.isEmpty()) {
            message.put("Error", "User does not exist in the database (incorrect user id)!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else if (isTeamInDB.isEmpty()) {
            message.put("Error", "Team does not exist in the database (incorrect team id)!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else if (userOwnsTeam.isPresent()) {
            message.put("Error", "User already owns this team!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            os.addConnection(owner);
            message.put("Message", "User is now the owner of this team!");
            return ResponseEntity.status(HttpStatus.OK).header("content-type", "application/json").body(message);
        }
    }

    @DeleteMapping("/delete/{ownerId}")
    public ResponseEntity<Object> deleteTeam(@PathVariable int ownerId) {
        Map<String, String> message = new HashMap<String, String>();
        if(os.getById(ownerId) == null)
        {
            message.put("Error", "Owner Connection doesn't exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if( os.deleteConnection(ownerId))
        {
            message.put("Message", "Connection deleted successfully");
            return ResponseEntity.status(200).header("content-type", "application/json").body(message);
        }
        else
        {
            message.put("Error", "Connections couldn't be deleted");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @DeleteMapping("/delete/{userId}/{teamId}")
    public ResponseEntity<Object> deleteTeam(@PathVariable int userId, @PathVariable int teamId) {
        Map<String, String> message = new HashMap<String, String>();
        if (or.findByIds(userId, teamId) == null)
        {
            message.put("Error", "Owner Connection doesn't exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if( os.deleteByUserTeamId(userId,teamId))
        {
            message.put("Message", "Connection deleted successfully");
            return ResponseEntity.status(200).header("content-type", "application/json").body(message);
        }
        else
        {
            message.put("Error", "Connections couldn't be deleted");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }
}