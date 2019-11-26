package ru.bscgrand.Zayavka.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bscgrand.Zayavka.Models.UserRepository;
import ru.bscgrand.Zayavka.Models.User;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/show")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        return userRepository.save(user);
    }
}
