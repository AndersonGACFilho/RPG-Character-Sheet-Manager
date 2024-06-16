package br.ufg.fullstack.rpg_character_sheet_manager.Repository.Attributes;

import jakarta.persistence.*;

import java.util.List;

/**
 * @brief A calculated attribute
 * @details A calculated attribute that is calculated based on the character's attributes
 * Basically, the calculated attributes are the character's damage, armor, and other attributes that are calculated
 */
@Entity()
public class CalculatedAttribute extends BasicAttribute {
    @Column(nullable = false)
    private int Increment;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<WeightAttribute> weightAttributes;

}
