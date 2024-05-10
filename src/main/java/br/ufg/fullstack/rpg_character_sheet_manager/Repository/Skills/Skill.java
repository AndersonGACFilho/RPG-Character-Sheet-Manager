package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Skills;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Attributes.WeightAttribute;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String ability;
    @Column(nullable = false)
    private Integer level;
    @Column(nullable = false)

    private Integer modifier;
    @Column(nullable = false)
    private Integer proficiency;

    @OneToMany()
    private List<WeightAttribute> attributes;
}
