package com.delala.delala.user;

import java.security.Principal;

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

    @GetMapping("/admin-registration")
    public String employerRegistration(Model model) {
        model.addAttribute("registrationObject", new AdminRegistration());
        return "admin-registration";
    }

    // @PostMapping("/talent-registration")
    // public String registerUser(TalentRegistration registrationObject) {

    //     User user = registrationObject.toUser(passwordEncoder);
    //     user = registeredUser(user, registrationObject.getSkill());
    //     userRepository.save(user);
    //     return "login";
    // }

    @PostMapping("/talent-registration")
    public String registerTalent(TalentRegistration registrationObject) {
        User talentUser = registrationObject.toUser(passwordEncoder);
        talentUser = registeredUser(talentUser, registrationObject.getSkill());
        userRepository.save(talentUser);
        return "login";
    }

    @PostMapping("/admin-registration")
    public String registerEmployer(AdminRegistration registrationObject) {
        User adminUser = registrationObject.toUser(passwordEncoder);
        adminUser.setSkill(skillRepository.findById(1L).get());
        userRepository.save(adminUser);
        return "login";
    }

    @GetMapping("/user-profile")
    public String userProfile(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user-profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(User user) {
        userRepository.save(user);
        return "redirect:/user-profile";
    }

    @PostMapping("/delete-account")
    public String deleteAccount(Principal principal) {
        userRepository.delete(userRepository.findByUsername(principal.getName()));
        return "redirect:/register"; // TODO: we need a generic registration page
    }

    // utility function
    public User registeredUser(User user, String skill_id) {
        user.setSkill(skillRepository.findById(Long.parseLong(skill_id)).get());
        return user;
    }
}
