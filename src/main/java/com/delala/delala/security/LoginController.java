package com.delala.delala.security;

import java.security.Principal;

import com.delala.delala.user.User;
import com.delala.delala.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/login")
    public String login(Model model, Principal principal) {
        User currentlyLoggedInUser = userRepository.findByUsername(principal.getName());

        if (currentlyLoggedInUser != null) {
            return "redirect:/logout";
        }
        model.addAttribute("userModel", new User());
        return "login";
    }
}