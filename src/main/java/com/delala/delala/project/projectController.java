package com.delala.delala.project;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.delala.delala.skill.Skill;
import com.delala.delala.skill.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller

public class projectController {

    @Autowired
    public ProjectService projectService;

    @Autowired
    public SkillRepository skillRepository;

    @GetMapping("/home")
    public ModelAndView relatedUpdates(Principal principal) {
        return projectService.relatedUpdates(principal);
    }

    // @GetMapping("/")
    // public ModelAndView relatedUpdates(@AuthenticationPrincipal User user){
    // return relatedUpdates(user);
    // }

    @GetMapping("/deleteProject")
    public String deleteProject(@RequestParam("projectId") Long id, HttpServletRequest httpServletRequest) {
        return projectService.deleteProject(id, httpServletRequest);
    }

    @GetMapping("/updateProject")
    public ModelAndView updateProject(@RequestParam("projectId") Long id) {
        return projectService.updateProject(id);
    }

    @PostMapping("/saveproject")
    public String saveProject(@Valid @ModelAttribute("project") Project project, 
            BindingResult bindingResult,Principal principal,Model model) {
        
        if (bindingResult.hasErrors()) {
            List<Skill>  skills =  ((List<Skill>) skillRepository.findAll());
            List<Skill> skillsSubList=skills.subList(2,skills.size());
            model.addAttribute("skills", skillsSubList);
            return "createproject";

        }
        return projectService.saveProject(project, principal);
    }

    @GetMapping("/projectList")
    public ModelAndView projectList() {
        return projectService.projectList();
    }

    @GetMapping("/myProjects")
    public ModelAndView myProjects(Principal principal) {
        return projectService.myProjects(principal);
    }

    @GetMapping("/createProject")
    public ModelAndView createProject() {
        return projectService.createProject();
    }

}
