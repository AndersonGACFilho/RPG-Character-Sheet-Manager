package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Sheets;

import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Attributes.BasicAttribute;
import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Attributes.CalculatedAttribute;
import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Classes.CharacterClass;
import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Dices.DiceLog;
import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.EquippedItems.EquippedItem;
import br.ufg.fullstack.rpg_character_sheet_manager.Repository.Items.Inventory.Inventory;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CharacterSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean IsInitiallySet = false;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String backgroundStory;
    @Column(nullable = false)
    private String alignment;

    @OneToOne
    private CharacterClass characterClass;

    @Column(nullable = false)
    private float money;
    @Column(nullable = false)
    private int experience;
    @Column(nullable = false)
    private int level;
    @Column(nullable = false)
    private int TotalPoints;
    @Column(nullable = false)
    private int usedPoints;

    @Column(nullable = false)
    private int lifePoints;
    @Column(nullable = false)
    private int initiative;

    /**
     * @brief This is the characters inventory
     */
    @OneToOne()
    private Inventory inventory;

    /**
     * @brief This list represents the equipped items attached to the player
     */
    @OneToMany()
    private List<EquippedItem> equippedItems;

    /**
     * @brief this is the player rolled dices history
     */
    @OneToMany()
    private List<DiceLog> rolledDices;

    /**
     * @brief A list of calculated attributes
     * @details A list of calculated attributes that are calculated based on the character's attributes
     * Basically, the calculated attributes are the character's damage, armor, and other attributes that are calculated
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CalculatedAttribute> calculatedAttributes;

    /**
     * @brief A list of attributes
     * @details A list of attributes that are used to calculate the character's calculated attributes
     * such as strength, dexterity, constitution, intelligence, wisdom, and charisma
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BasicAttribute> attributes;

}
