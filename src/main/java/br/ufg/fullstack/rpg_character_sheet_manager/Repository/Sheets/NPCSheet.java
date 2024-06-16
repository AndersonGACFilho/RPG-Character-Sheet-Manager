package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Sheets;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class NPCSheet extends CharacterSheet {

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String IsAnEnemy;
}
