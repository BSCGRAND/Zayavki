package ru.bscgrand.Zayavka.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bscgrand.Zayavka.Models.UserRepository;
import ru.bscgrand.Zayavka.Models.Users;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/show")
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }
    @PostMapping("/create")
    public Users createUser(@RequestBody Users users){
        Users usersCreate = new Users();
        usersCreate.setName(users.getName());
        usersCreate.setEmail(users.getEmail());
        usersCreate.setPassword(users.getPassword());
        return userRepository.save(users);
    }
}
