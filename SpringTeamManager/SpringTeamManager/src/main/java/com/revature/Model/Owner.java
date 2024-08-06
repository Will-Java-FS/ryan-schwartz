package com.revature.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "owners")
@NoArgsConstructor
public class Owner {
    public Owner(int user_id, int team_id) {
        this.user_id = user_id;
        this.team_id = team_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id", updatable = false)
    @Getter
    private int owner_id;

    @Setter
    @Getter
    private int user_id;

    @Setter
    @Getter
    private int team_id;

    @Override
    public String toString() {
        return "Owner{" +
                ", user_id='" + user_id + '\'' +
                ", team_id='" + team_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return user_id == owner.user_id && team_id == owner.team_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner_id, user_id, team_id);
    }
}

