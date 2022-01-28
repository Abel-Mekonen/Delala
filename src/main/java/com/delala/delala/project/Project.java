package com.delala.delala.project;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.delala.delala.skill.Skill;
import com.delala.delala.user.User;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Project title is required")
    @Size(min=5,max=15)
    private String projectTitle;
    @NotBlank(message = "Project description is required")
    @Size(min=10,max=100)
    private String projectDescription;
    @Min(10)
    private float price;
    @ManyToOne
    @ToString.Exclude
    private Skill skill;
    @ManyToOne
    @ToString.Exclude
    private User user;
}
