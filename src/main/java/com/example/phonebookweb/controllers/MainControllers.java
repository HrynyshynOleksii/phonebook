package com.example.phonebookweb.controllers;


import com.example.phonebookweb.repo.PhoneRepository;
import com.example.phonebookweb.models.PhoneNumber;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainControllers {

    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping("/index")
    public String phoneBookList(Model model,Long id){

        Iterable<PhoneNumber> phoneNumbers = phoneRepository.findAll();
        model.addAttribute("contact", phoneNumbers);
        return "index";
    }

    @GetMapping("/sortByName")
    public String sortByName (Model model,Long id){

        Iterable<PhoneNumber> phoneNumbers = phoneRepository.findAll(Sort.by(Sort.Direction.ASC, "fullName"));
        model.addAttribute("contact", phoneNumbers);

        return "/index";
    }

    @GetMapping("/sortByEmail")
    public String sortByEmail (Model model,Long id){

        Iterable<PhoneNumber> phoneNumbers = phoneRepository.findAll(Sort.by(Sort.Direction.ASC, "email"));
        model.addAttribute("contact", phoneNumbers);

        return "/index";
    }

    @GetMapping("/{id}/remove")
    public String contactRemove(@PathVariable(value = "id") long id, Model model) {
        if (!phoneRepository.existsById(id)) {
            return "index";
        }

        PhoneNumber phoneNumber = phoneRepository.findById(id).orElseThrow(null);
        phoneRepository.delete(phoneNumber);
        System.out.println(phoneNumber.getId());
        return "redirect:/index";
    }

    @GetMapping("/{id}/view")
    public String contactView(@PathVariable(value = "id") long id, Model model) {
        if (!phoneRepository.existsById(id)) {
            return "index.html";
        }
        Optional<PhoneNumber> phoneNumber = phoneRepository.findById(id);
        ArrayList<PhoneNumber> res = new ArrayList<>();
        phoneNumber.ifPresent(res::add);
        model.addAttribute("contact", res);

        return "view-contact";
    }

    @GetMapping("/add")
    public String addContact ( Model model){
        return "add";
    }

    @PostMapping("/add")
    public String saveContact (@RequestParam String fullName,
                              @RequestParam Long number,
                              @RequestParam String email,
                              Model model){
        PhoneNumber phoneNumber = new PhoneNumber(fullName, number, email);
        phoneRepository.save(phoneNumber);

        return "redirect:/index";


    }


}

