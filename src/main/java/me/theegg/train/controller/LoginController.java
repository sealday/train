package me.theegg.train.controller;

import me.theegg.train.model.User;
import me.theegg.train.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


/**
 * Created by seal on 9/16/15.
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        System.out.println(user);
        if (userRepository.exists(user.getUsername())) {
            User u = userRepository.findOne(user.getUsername());
            System.out.println(u);
            if (u != null && u.getPassword().equals(user.getPassword())) {
                UUID uuid = UUID.randomUUID();
                String token = uuid.toString();
                u.setToken(token);
                userRepository.save(u);
                return ResponseEntity.ok(u);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println(user);
        if (userRepository.exists(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }
    }
}
