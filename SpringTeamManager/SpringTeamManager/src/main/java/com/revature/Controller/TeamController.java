package com.revature.Controller;

import com.revature.Model.Team;
import com.revature.Services.TeamServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teams")
@CrossOrigin
public class TeamController {
    TeamServ ts;

    @Autowired
    public TeamController(TeamServ ts) {
        this.ts = ts;
    }

    @GetMapping("/secret")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping
    public ResponseEntity<Object> getAllTeams() {
        return ResponseEntity.status(200).header("content-type", "application/json").body(ts.getAllTeams());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Object> getTeam(@PathVariable int teamId) {
        return ResponseEntity.status(200).header("content-type", "application/json").body(ts.getTeam(teamId));
    }

    @GetMapping("/name/{teamName}")
    public ResponseEntity<Object> getTeam(@PathVariable String teamName) {
        return ResponseEntity.status(200).header("content-type", "application/json").body(ts.getTeam(teamName));
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Object> deleteTeam(@PathVariable int teamId) {
        Map<String, String> message = new HashMap<String, String>();
        if( ts.deleteTeam(teamId))
        {
            message.put("Message", "Team at " + teamId + " deleted successfully");
            return ResponseEntity.status(200).header("content-type", "application/json").body(message);
        }
        else
        {
            message.put("Error", "Team at " + teamId + " couldn't be deleted");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

}
