package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Attributes;

import jakarta.persistence.*;

@Entity()
public class BasicAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int description;
    @Column(nullable = false)
    private int value;

}
