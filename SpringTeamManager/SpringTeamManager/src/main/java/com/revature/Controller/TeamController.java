package com.revature.Controller;

import com.revature.Model.Team;
import com.revature.Model.User;
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

    @PostMapping(value = "/add", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Object> addTeam(@RequestBody Team team)
    {
        Map<String, String> message = new HashMap<String, String>();
        Team success = ts.addTeam(team);
        if(success == null)
        {
            message.put("Error", "Team was not added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else
        {
            message.put("Message", "Team updated");
            return ResponseEntity.status(200).header("content-type", "application/json").body(message);
        }

    }

    @PatchMapping(value = "/update", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Object> updateTeam(@RequestBody Team team)
    {
        Map<String, String> message = new HashMap<String, String>();
        Team success = ts.updateTeam(team);
        if(success == null)
        {
            message.put("Error", "Team at " + team.getId() + "  was not updated. Team may not have been added.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else
        {
            message.put("Message", "Team at " + team.getId() + " updated");
            return ResponseEntity.status(200).header("content-type", "application/json").body(message);
        }

    }
}
