package com.delala.delala.project;

public enum Skill {

    GRAPHICDESIGN("Graphics Design"),
    LOGISTICS("Logistics"),
    ILLUSTRATION("Illustration"),
    BLOGGING("Blogging");


    private String skillName;

    Skill(String skillName){
        this.skillName=skillName;
    }

    public String getSkillName(){
        return skillName;
    }


    
}
