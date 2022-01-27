package com.delala.delala.project;

import com.delala.delala.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class projectController {
    
    @Autowired
    public ProjectService projectService;
    

    @GetMapping("/")
    public ModelAndView relatedUpdates(@AuthenticationPrincipal User user){
        return relatedUpdates(user);
    }

    @GetMapping("/deleteProject")
    public String deleteProject(@RequestAttribute("projectId") Long id){
        return projectService.deleteProject(id);
    }

    @GetMapping("/updateProject")
    public ModelAndView updateProject(@RequestAttribute("projectId") Long id){
        return projectService.updateProject(id);
    }
    
    @PostMapping("/saveProject")
    public String saveProject(@ModelAttribute("project")Project project){
        return projectService.saveProject(project);
    }

    @GetMapping("/projectList")
    public ModelAndView projectList(){
        return projectService.projectList();
    }


}
