package com.delala.delala.project;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.delala.delala.skill.Skill;
import com.delala.delala.skill.SkillRepository;
import com.delala.delala.user.User;
import com.delala.delala.user.UserRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ProjectService {

    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public SkillRepository skillRepository;

    @Autowired
    public UserRepository userRepository;

    public ModelAndView relatedUpdates(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("home");
        User user = userRepository.findByUsername(principal.getName());
        List<Project> projects = projectRepository.findBySkill(user.getSkill());
        modelAndView.addObject("projects", projects);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    public String deleteProject(Long id, HttpServletRequest httpServletRequest) {
        projectRepository.deleteById(id);
        return "redirect:" + httpServletRequest.getHeader("Referer");
    }

    public ModelAndView createProject() {
        ModelAndView modelAndView = new ModelAndView("createproject");
        modelAndView.addObject("project", new Project());
        List<Skill>  skills =  ((List<Skill>) skillRepository.findAll());
        List<Skill> skillsSubList=skills.subList(2,skills.size());
        modelAndView.addObject("skills",skillsSubList);
        return modelAndView;
    }

    public ModelAndView updateProject(Long id) {
        ModelAndView modelAndView = new ModelAndView("editproject");
        Project project = projectRepository.findById(id).get();
        List<Skill>  skills =  ((List<Skill>) skillRepository.findAll());
        List<Skill> skillsSubList=skills.subList(2,skills.size());
        modelAndView.addObject("skills",skillsSubList);
        modelAndView.addObject("project", project);
        return modelAndView;

    }

    public String saveProject(Project project,Principal principal,HttpServletRequest request) {
        User user = userRepository.findByUsername(principal.getName());
        project.setUser(user);
        projectRepository.save(project);
        return "redirect:/myProjects";
    }

    public ModelAndView projectList() {
        ModelAndView modelAndView = new ModelAndView("admin-projects");
        List<Project> projects = projectRepository.findAll();
        modelAndView.addObject("projects", projects);
        return modelAndView;
    }

    public ModelAndView myProjects(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("myprojects");
        User user = userRepository.findByUsername(principal.getName());
        List<Project> projects = projectRepository.findByUser(user);
        modelAndView.addObject("projects", projects);
        return modelAndView;
    }

}
