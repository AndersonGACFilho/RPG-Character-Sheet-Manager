package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.EquippedItems;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class EquippedItem extends Item {

    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;
    @Column(nullable = false)
    private int IsEquipped;

}
