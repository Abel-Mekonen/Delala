package com.delala.delala.skill;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {
    public Optional<Skill> findById(Long id); 
}
