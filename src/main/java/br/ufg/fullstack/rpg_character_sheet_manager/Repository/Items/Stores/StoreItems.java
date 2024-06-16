package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.Stores;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class StoreItems extends Item {
    @Column(nullable = false)
    private boolean isAvailable;
}
