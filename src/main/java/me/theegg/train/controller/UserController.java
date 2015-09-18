package me.theegg.train.controller;

import me.theegg.train.model.Tessera;
import me.theegg.train.model.User;
import me.theegg.train.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

/**
 * Created by seal on 9/16/15.
 */
@RestController()
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUser() {
        return userRepository.findAll();
    }

}
