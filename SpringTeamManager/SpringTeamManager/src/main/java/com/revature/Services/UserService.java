package com.revature.Services;

import com.revature.Model.User;
import com.revature.Model.Team;
import java.util.List;

public interface UserService {
    // Trivial Services
    public User createUserAccount(User u);
    public User userLogin(User u);
    public List<Team> getItemsAssociatedWithUser(User u);
}





