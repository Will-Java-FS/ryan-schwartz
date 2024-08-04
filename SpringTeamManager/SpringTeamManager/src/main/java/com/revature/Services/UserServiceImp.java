package com.revature.Services;
import com.revature.Model.User;
import com.revature.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) { this.userRepository = userRepository; }

    public User createUserAccount(User user) { return userRepository.save(user); }
    public User userLogin(User user) { return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()); }
}
