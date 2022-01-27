package com.delala.delala.project;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.delala.delala.skill.Skill;

import lombok.Data;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String projectTitle;
    private String projectDescription;
    private float price;
    private Skill skilReq;


    
}
