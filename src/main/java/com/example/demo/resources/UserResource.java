package com.example.demo.resources;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserResource {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    UserService userService;

    @GetMapping("/hi/{name}")
    public String sayHello(@RequestHeader(name="Accept-language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message",null,locale);

    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id) {
        User user = userService.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id - " + id);
        }
        return user;
    }

    @PostMapping("/create_user")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User savedUser = userService.saveUser(user);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }


    @GetMapping("/delete_user/{id}")
    public void deleteUser(@PathVariable Integer id) {
        User user =  userService.deleteUser(id);
        if(user == null)
            throw new UserNotFoundException("id - "+id);
    }
}
