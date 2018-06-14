package com.example.demo.services;

import com.example.demo.entities.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    static List<User> users = new ArrayList<User>();

    static int count = 2;

    static {
        users.add(new User(1, "bhuvan",new Date()));
        users.add(new User(2, "bhargav",new Date()));
    }

    public List<User> findAll() {
        return users;
    }


    public User findOne(int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User saveUser(User user) {
        if(user == null) return  null;
        if (user.getId() == null) {
            count++;
            user.setId(count);
        }
        users.add(user);
        return user;
    }

    public User deleteUser(int id) {
        Iterator<User> itr = users.iterator();
        while (itr.hasNext()) {
            User user = itr.next();
            if(user.getId() == id) {
                itr.remove();
                return user;
            }
        }
        return null;
    }

}
