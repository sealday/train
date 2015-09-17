package me.theegg.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by seal on 9/18/15.
 */
@Controller
public class AdminController {

    @RequestMapping({"/admin", "/admin/"})
    public String home() {
        return "forward:/admin/index.html";
    }
}
