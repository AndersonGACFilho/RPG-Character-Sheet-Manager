package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Skills;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SkillType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

    @OneToMany()
    private List<Skill> skills;

}
