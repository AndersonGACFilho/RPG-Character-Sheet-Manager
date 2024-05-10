package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Attributes;

import jakarta.persistence.*;

@Entity
public class WeightAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private BasicAttribute basicAttribute;

    @Column(nullable = false)
    private int weight;

}
