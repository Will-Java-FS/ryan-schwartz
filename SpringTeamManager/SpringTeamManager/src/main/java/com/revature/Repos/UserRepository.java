package com.revature.Repos;

// import com.revature.Model.Team;
import com.revature.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
      User findByUsername(String name);
}