package Repos;

import Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * from users where username = ?1 Order by user_id", nativeQuery = true)
    List<User> findByUsername(String name);


    @Query(value = "SELECT * from users where username = ?1 And pass = ?2", nativeQuery = true)
    User findByLogin(String name, String pass);

    @Query(value = "Select * from users where users.my_team_id = (Select team_id from teams where team_name = 'MF Gators')"
            , nativeQuery = true)
    List<User> findByTeamName(String name);
}