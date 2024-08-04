package com.revature.Repos;

import com.revature.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
      @Query(value = "SELECT * FROM users WHERE user_id = ?1", nativeQuery = true)
      User findByUserId(int userId);
      User findByUsername(String username);
      User findByUsernameAndPassword(String username, String password);
}