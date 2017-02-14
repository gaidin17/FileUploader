package com.gaidin17.service;

import com.gaidin17.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeny_Akulenko on 2/14/2017.
 */
@Component
public class UserService {
    private List<User> userList;

    public UserService() {
        this.userList = new ArrayList<>();
    }

    public User getUserById(String userId) {
        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public void addUserToSystem(User user) {
        userList.add(user);
    }

    public boolean isUserInSystem(String userId) {
        for (User user : userList) {
            if (user.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
