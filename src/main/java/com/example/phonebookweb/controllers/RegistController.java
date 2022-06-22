package com.example.phonebookweb.controllers;

import com.example.phonebookweb.config.Role;
import com.example.phonebookweb.config.User;
import com.example.phonebookweb.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.CollationElementIterator;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "/registration";
    }

    @PostMapping("/registration")
    public String AddUser(User user, Map<String, Object> model,
                          @RequestParam String username,
                          @RequestParam String password, @RequestParam String confirmPassword) {

        if (!password.equals(confirmPassword)){
            model.put("message", "Паролі не співпадають");
            return "/registration";
        }
        if (password.length()==0){
            model.put("message", "Field (Password) can't be empty! ");
            return "/registration";
        }


        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.put("message", "User exists!");
            return "/registration";
        }

        if (!password.equals(confirmPassword)){
            model.put("message", "Паролі не співпадають");
            return "/registration";

        }



        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:login";
    }


}
