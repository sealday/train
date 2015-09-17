package me.theegg.train.controller;

import me.theegg.train.model.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by seal on 9/18/15.
 */
@ControllerAdvice
public class MyControllerAdvice {

    @ModelAttribute("user")
    User getUser(HttpServletRequest request) {
        return (User) request.getAttribute("user");
    }

}
