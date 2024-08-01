package com.revature.Services;
import com.revature.Model.Team;
import com.revature.Model.User;
import com.revature.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUserAccount(User user) { return userRepository.save(user); }


    public User userLogin(User u) {
        return null;
    }

    public List<Team> getItemsAssociatedWithUser(User u) {
        return null;
    }

}
