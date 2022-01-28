package com.delala.delala.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.delala.delala.skill.SkillRepository;
import com.delala.delala.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ProjectService {

    @Autowired
    public ProjectRepository projectRepository;

    @Autowired
    public SkillRepository skillRepository;

    public ModelAndView relatedUpdates(User user) {
        ModelAndView modelAndView = new ModelAndView("home");
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
        modelAndView.addObject("skills", skillRepository.findAll());
        return modelAndView;
    }

    public ModelAndView updateProject(Long id) {
        ModelAndView modelAndView = new ModelAndView("");
        Project project = projectRepository.findById(id).get();
        modelAndView.addObject("project", project);
        return modelAndView;

    }

    public String saveProject(Project project, HttpServletRequest httpServletRequest) {
        projectRepository.save(project);
        return "redirect:" + httpServletRequest.getHeader("referer");
    }

    public ModelAndView projectList() {
        ModelAndView modelAndView = new ModelAndView("admin-projects");
        List<Project> projects = projectRepository.findAll();
        modelAndView.addObject("projects", projects);
        return modelAndView;
    }

}
