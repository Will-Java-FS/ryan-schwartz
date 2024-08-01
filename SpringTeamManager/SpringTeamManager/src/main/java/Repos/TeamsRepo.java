package Repos;

import Model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamsRepo extends CrudRepository<Team, Integer> {

    @Query(value = "SELECT * from teams where team_name = ?1 Order by team_id", nativeQuery = true)
    List<Team> findByName(String name);

}