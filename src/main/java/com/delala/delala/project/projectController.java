package com.delala.delala.project;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class projectController {

    @Autowired
    public ProjectService projectService;

    @GetMapping("/home")
    public ModelAndView relatedUpdates(Principal principal) {
        return projectService.relatedUpdates(principal);
    }

    // @GetMapping("/")
    // public ModelAndView relatedUpdates(@AuthenticationPrincipal User user){
    // return relatedUpdates(user);
    // }

    @GetMapping("/deleteProject")
    public String deleteProject(@RequestAttribute("projectId") Long id, HttpServletRequest httpServletRequest) {
        return projectService.deleteProject(id, httpServletRequest);
    }

    @GetMapping("/updateProject")
    public ModelAndView updateProject(@RequestAttribute("projectId") Long id) {
        return projectService.updateProject(id);
    }

    @PostMapping("/saveProject")
    public String saveProject(@Valid @ModelAttribute("project") Project project, HttpServletRequest httpServletRequest,
            BindingResult bindingResult,Principal principal) {
        if (bindingResult.hasErrors()) {
            return "createproject";
        }
        return projectService.saveProject(project, httpServletRequest,principal);
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
