package com.revature.Controller;

import com.revature.Model.Team;
import com.revature.Repos.TeamsRepo;
import com.revature.Services.TeamServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
@CrossOrigin
public class TeamController {
    TeamServ ts;
    TeamsRepo tr;

    @Autowired
    public TeamController(TeamServ ts, TeamsRepo tr) {
        this.ts = ts;
        this.tr = tr;
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
        Optional<Team> isTeamInDB = Optional.ofNullable(tr.findByTeamId(teamId));
        Map<String, String> message = new HashMap<String, String>();

        if (isTeamInDB.isEmpty()) {
            message.put("Error", "Team does not exist in the database (incorrect team id)!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("content-type", "application/json").body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).header("content-type", "application/json").body(ts.getTeam(teamId));
        }
    }

    @GetMapping("/name/{teamName}")
    public ResponseEntity<Object> getTeam(@PathVariable String teamName) {
        Optional<Team> isTeamInDB = Optional.ofNullable(tr.findByName(teamName));
        Map<String, String> message = new HashMap<String, String>();

        if (isTeamInDB.isEmpty()) {
            message.put("Error", "Team does not exist in the database (incorrect team name)!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("content-type", "application/json").body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).header("content-type", "application/json").body(ts.getTeam(teamName));
        }
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Object> deleteTeam(@PathVariable int teamId) {
        Map<String, String> message = new HashMap<String, String>();

        if (ts.getTeam(teamId) == null) {
            message.put("Error", "Team at " + teamId + " doesn't exist.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        if (ts.deleteTeam(teamId)) {
            message.put("Message", "Team at " + teamId + " was deleted successfully!");
            return ResponseEntity.status(HttpStatus.OK).header("content-type", "application/json").body(message);
        } else {
            message.put("Error", "Team at " + teamId + " couldn't be deleted.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @PostMapping(value = "/add", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Object> addTeam(@RequestBody Team team)
    {
        Map<String, String> message = new HashMap<String, String>();
        Optional<Team> isTeamNameTaken = Optional.ofNullable(tr.findByName(team.getTeam_name()));

        if (isTeamNameTaken.isPresent()) {
            message.put("Error", "Team name is already being used. Must be unique!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            ts.addTeam(team);
            message.put("Message", "Team was successfully added!");
            return ResponseEntity.status(HttpStatus.OK).header("content-type", "application/json").body(message);
        }

    }

    @PatchMapping(value = "/update", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Object> updateTeam(@RequestBody Team team) {
        Map<String, String> message = new HashMap<String, String>();
        Optional<Team> isTeamIdInDB = Optional.ofNullable(tr.findByTeamId(team.getId()));
        Optional<Team> isTeamNameTaken = Optional.ofNullable(tr.findByName(team.getTeam_name()));

        if (isTeamIdInDB.isEmpty()) {
            message.put("Error", "Team does not exist in the database (incorrect team id)!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else if (isTeamNameTaken.isPresent()) {
            message.put("Error", "Team name is already being used. Must be unique!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            ts.updateTeam(team);
            message.put("Message", "Team was successfully updated!");
            return ResponseEntity.status(HttpStatus.OK).header("content-type", "application/json").body(message);
        }
    }
}
