package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Classes;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Skills.Skill;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CharacterClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

    @OneToMany()
    private List<CharacterSubclass> subclasses;
    @OneToMany()
    private List<Skill> allClassSkills;
    @OneToMany()
    private List<Skill> availableSkills;
    @OneToMany()
    private List<Skill> equippedSkills;
}
