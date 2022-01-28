package com.delala.delala;

import com.delala.delala.skill.Skill;
import com.delala.delala.skill.SkillRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DelalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DelalaApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(SkillRepository repo) {
		return args -> {
			repo.save(new Skill("Software Engineer"));
			repo.save(new Skill("Electrical Engineer"));
			repo.save(new Skill("UX Designer"));
			repo.save(new Skill("Tutor"));
		};
	}
}
