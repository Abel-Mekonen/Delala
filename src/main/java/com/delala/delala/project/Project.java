package com.delala.delala.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.delala.delala.skill.Skill;
import com.delala.delala.user.User;



import lombok.Data;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Project title is required")
    private String projectTitle;
    @NotBlank(message = "Project description is required")
    private String projectDescription;
    @Min(10)
    private float price;
    @ManyToOne
    private Skill skill;
    @ManyToOne
    private User user;
}
