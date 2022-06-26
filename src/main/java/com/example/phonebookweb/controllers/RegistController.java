package com.example.phonebookweb.controllers;

import com.example.phonebookweb.config.Role;
import com.example.phonebookweb.config.User;
import com.example.phonebookweb.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import java.text.CollationElementIterator;
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


        if (username.length()==0){
            model.put("errorUsername", "Username is required!");
            return "/registration";
        }

        if (password.length() < 4 || confirmPassword.length() < 4 ){
            model.put("errorPassword", "Password must be at least 4 characters!");
            return "/registration";
        }
//
//        if (!password.equals(confirmPassword)){
//            model.put("passDismatch", passDismatch);
//            return "/registration";
//        }

        if (!password.equals(confirmPassword)){
            model.put("passDismatch", "Fields do not match");
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
