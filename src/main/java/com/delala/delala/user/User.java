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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;
import com.delala.delala.skill.Skill;
import com.delala.delala.project.Project;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;
    @NotNull(message = "First name is required")
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    private String email;
    private String role;
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Project> projects;
}
