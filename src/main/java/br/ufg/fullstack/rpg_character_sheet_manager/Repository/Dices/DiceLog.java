package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Dices;

import jakarta.persistence.*;

@Entity
public class DiceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer diceType;
    @Column(nullable = false)
    private Integer diceValue;
}
