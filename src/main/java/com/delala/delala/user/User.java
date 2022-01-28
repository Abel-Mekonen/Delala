package com.delala.delala.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import com.delala.delala.skill.Skill;
import com.delala.delala.project.Project;
import lombok.Data;

@Data
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name="skill_id")
    private Skill skill;

    @OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
    private List<Project> projects;

    
    
}
