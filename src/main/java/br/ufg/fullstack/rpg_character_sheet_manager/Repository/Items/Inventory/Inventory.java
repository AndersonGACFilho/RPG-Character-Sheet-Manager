package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.Inventory;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.Item;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany()
    private List<Item> items;

}
