package com.delala.delala.skill;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {

    
}
