package com.delala.delala.user;

import com.delala.delala.skill.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SkillRepository skillRepository;
    
    @GetMapping("/talent-registration")
    public String talentRegistration(Model model) {
        model.addAttribute("registrationObject", new TalentRegistration());
        model.addAttribute("skills", skillRepository.findAll());
        return "talent-registration";
    }

    @GetMapping("/employer-registration")
    public String employerRegistration(Model model) {
        model.addAttribute("registrationObject", new EmployerRegistration());
        return "employer-registration";
    }

    @PostMapping("/register")
    public String registerUser(TalentRegistration registrationObject) {

        User user = registrationObject.toUser(passwordEncoder);
        user = registeredUser(user, registrationObject.getSkill());
        userRepository.save(user);
        return "login";
    }

    public User registeredUser(User user, String skill_id) {
        user.setSkill(skillRepository.findById(Long.parseLong(skill_id)).get());
        return user;
    }
}
