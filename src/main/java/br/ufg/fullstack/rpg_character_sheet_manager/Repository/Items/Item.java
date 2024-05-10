package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Attributes.BasicAttribute;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean isToConsume;
    @Column(nullable = false)
    private boolean isSellable;
    @Column(nullable = false)
    private boolean isStackable;

    @Column(nullable = false)
    private float price;
    @OneToMany()
    private List<BasicAttribute> basicAttributes;

}
