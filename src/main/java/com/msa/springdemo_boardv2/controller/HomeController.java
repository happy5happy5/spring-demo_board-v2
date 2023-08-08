package com.msa.springdemo_boardv2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String home(Model model){
        System.out.println("[HOME CONTROLLER]......");
        model.addAttribute("message","Hello, World!");
        return "index";
    }
}
