package com.example.phonebookweb.controllers;


import com.example.phonebookweb.repo.PhoneRepository;
import com.example.phonebookweb.models.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class MainControllers {

    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping("/index")
    public String PhoneBookList(Model model){
        Iterable<PhoneNumber> phoneNumbers = phoneRepository.findAll();
        model.addAttribute("contact", phoneNumbers);
        return "index";


    }

    @GetMapping("/{id}/remove")
    public String contactRemove(@PathVariable(value = "id") long id, Model model) {
        if (!phoneRepository.existsById(id)) {
            return "index";
        }
        PhoneNumber phoneNumber = phoneRepository.findById(id).orElseThrow(null);
        phoneRepository.delete(phoneNumber);
        return "index";

    }


}

