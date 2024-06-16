package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class CharacterSubclass extends CharacterClass {
    @Column(nullable = false)
    private String preRequisites;
}
