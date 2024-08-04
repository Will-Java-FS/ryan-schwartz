package com.revature.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "teams")

public class Team {

    public Team() {} // Possibly unnecessary code?

    public Team(String team_name) {
        this.team_name = team_name;
        this.wins = 0;
        this.loses = 0;
    }

    public Team(int id, String team_name) {
        this.id = id;
        this.team_name = team_name;
        this.wins = 0;
        this.loses = 0;
    }

    public Team(int id, String team_name, int wins, int loses) {
        this.id = id;
        this.team_name = team_name;
        this.wins = wins;
        this.loses = loses;
    }
    public Team(String team_name, int wins, int loses) {

        this.team_name = team_name;
        this.wins = wins;
        this.loses = loses;
    }

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", updatable = false)
    private int id;

    @Setter
    @Getter
    @Column(name = "team_name", nullable = false, unique = true)
    private String team_name;

    @Setter
    @Getter
    @Column(name = "wins", columnDefinition = "int CHECK (wins >= 0)")
    private int wins;

    @Setter
    @Getter
    @Column(name = "loses", columnDefinition = "int CHECK (loses >= 0)")
    private int loses;

    @Override
    public String toString() {
        return "Team{" +
                "team_name='" + team_name + '\'' +
                ", wins=" + wins +
                ", loses=" + loses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id && getWins() == team.getWins() && getLoses() == team.getLoses() && Objects.equals(getTeam_name(), team.getTeam_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getTeam_name(), getWins(), getLoses());
    }
}

