package com.delala.delala.skill;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import com.delala.delala.project.Project;
import com.delala.delala.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Skill {

    public Skill(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    
    //added mapped by
    @OneToMany(mappedBy="skill")
    private List<User> users;

    //added mapped by
    @OneToMany(mappedBy="skill")
    private List<Project> projects;
}
