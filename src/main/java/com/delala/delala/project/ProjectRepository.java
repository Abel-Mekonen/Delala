package com.delala.delala.project;

import java.util.List;

import com.delala.delala.skill.Skill;
import com.delala.delala.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    public List<Project> findBySkill(Skill skill);

    public List<Project> findByUser(User user);
}
