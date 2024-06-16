package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.Stores;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Sheets.NPCSheet;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private String alignment;


    @OneToOne()
    private NPCSheet OwnerNPC;

    @OneToMany()
    private List<StoreItems> items;
}
