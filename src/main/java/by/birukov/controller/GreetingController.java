package by.birukov.controller;

import by.birukov.entity.User;
import by.birukov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String goToMain(){
        return "greeting";
    }
}
