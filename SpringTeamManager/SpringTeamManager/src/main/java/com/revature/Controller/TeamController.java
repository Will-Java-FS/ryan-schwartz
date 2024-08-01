package com.revature.Controller;

import com.revature.Servs.TeamServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
