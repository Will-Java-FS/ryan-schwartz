package com.revature.Repos;

import com.revature.Model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepo extends CrudRepository<Team, Integer> {
    @Query(value = "SELECT * from teams WHERE team_name = ?1", nativeQuery = true)
    Team findByName(String name);

    @Query(value = "SELECT * from teams WHERE team_id = ?1", nativeQuery = true)
    Team findByTeamId(int teamId);
}