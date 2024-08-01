package com.revature.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private int user_id;

    @Setter
    @Getter
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Setter
    @Getter
    @Column(name = "pass")
    private String pass;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "my_team_id", referencedColumnName = "team_id")
    private Team my_team;

    public User(String username, String pass, Team my_team) {
        this.username = username;
        this.pass = pass;
        this.my_team = my_team;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", my_team=" + my_team +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPass(), user.getPass()) && Objects.equals(getMy_team(), user.getMy_team());
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, getUsername(), getPass(), getMy_team());
    }
}
