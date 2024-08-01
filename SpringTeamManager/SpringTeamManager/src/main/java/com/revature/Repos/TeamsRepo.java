package com.revature.Repos;

import com.revature.Model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepo extends CrudRepository<Team, Integer> {

    @Query(value = "SELECT * from teams where team_name = ?1 Order by team_id", nativeQuery = true)
    Team findByName(String name);

}