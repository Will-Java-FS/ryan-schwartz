package com.revature.Controller;
import com.revature.Model.Owner;
import com.revature.Model.Team;
import com.revature.Model.User;
import com.revature.Services.OwnerService;
import com.revature.Services.TeamServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/owner")
@CrossOrigin
public class OwnerController {
    OwnerService os;

    @Autowired
    public OwnerController(OwnerService os) {
        this.os = os;
    }

    @PostMapping(value = "/add", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Object> addTeam(@RequestBody Owner owner)
    {
        Map<String, String> message = new HashMap<String, String>();
        Owner success = os.addConnection(owner);
        if(success == null)
        {
            message.put("Error", "Owner connection couldn't be made");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else
        {
            message.put("Message", "Owner connected successful");
            return ResponseEntity.status(200).header("content-type", "application/json").body(message);
        }

    }

    @DeleteMapping("/delete/{ownerId}")
    public ResponseEntity<Object> deleteTeam(@PathVariable int ownerId) {
        Map<String, String> message = new HashMap<String, String>();

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