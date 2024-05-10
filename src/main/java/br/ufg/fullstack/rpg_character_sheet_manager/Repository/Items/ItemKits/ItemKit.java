package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.ItemKits;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.Item;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ItemKit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private boolean isAStarterKit;

    @OneToMany()
    private List<Item> items;
}
