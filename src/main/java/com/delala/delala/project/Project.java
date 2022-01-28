package com.delala.delala.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private float price;
    @ManyToOne
    private Skill skill;
    @ManyToOne
    private User user;
}
