package com.delala.delala.user;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.delala.delala.skill.Skill;
import com.delala.delala.skill.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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
        List<Skill>  skills =  ((List<Skill>) skillRepository.findAll());
        List<Skill> skillsSubList=skills.subList(2,skills.size());
        model.addAttribute("skills",skillsSubList);
        return "talent-registration";
    }

    @GetMapping("/admin-registration")
    public String employerRegistration(Model model) {
        model.addAttribute("registrationObject", new AdminRegistration());
        return "admin-registration";
    }

    // @PostMapping("/talent-registration")
    // public String registerUser(TalentRegistration registrationObject) {

    // User user = registrationObject.toUser(passwordEncoder);
    // user = registeredUser(user, registrationObject.getSkill());
    // userRepository.save(user);
    // return "login";
    // }
    @GetMapping("/registerEmployer")
    public ModelAndView registerEmployer() {
        ModelAndView modelAndView = new ModelAndView("employer-registration");
        modelAndView.addObject("registerEmployer", new EmployerRegistration());
        return modelAndView;
    }

    @PostMapping("/registerEmployer")
    public String registerEmployer(@Valid @ModelAttribute("registerEmployer") EmployerRegistration employerRegistration,
            Errors errors) {
        if (errors.hasErrors()) {
            log.error("Employer registration failed due to validation error : \n{}", errors);
            return "employer-registration";
        }
        User user = employerRegistration.toUser(passwordEncoder);
        user.setSkill(skillRepository.findById(Long.parseLong("2")).get());
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("users")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView("admin-users");
        List<User> users = (List<User>) userRepository.findAll();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @PostMapping("/talent-registration")
    public String registerTalent(@Valid @ModelAttribute("registrationObject") TalentRegistration registrationObject,
            Errors errors) {

        
        if (errors.hasErrors()) {
            log.error("Validation error on register talent : /n {}", errors);
            return "talent-registration";
        }
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
        List<Skill>  skills =  ((List<Skill>) skillRepository.findAll());
        List<Skill> skillsSubList=skills.subList(2,skills.size());
        model.addAttribute("skills",skillsSubList);
        return "editProfile";
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

    @PostMapping("/delete-user/{id}")
    public String deleteProject(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        userRepository.deleteById(id);
        return "redirect:" + httpServletRequest.getHeader("Referer");
    }
}
