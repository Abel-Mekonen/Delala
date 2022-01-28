package com.delala.delala.project;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.delala.delala.skill.Skill;
import com.delala.delala.user.User;



import lombok.Data;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String projectTitle;
    private String projectDescription;
    private String price;

    @ManyToOne
    @JoinColumn(name="skill_id")
    private Skill skillReq;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    
}
