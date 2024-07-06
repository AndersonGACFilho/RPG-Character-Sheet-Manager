package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class CharacterAlignment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the character alignment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the character alignment.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The description of the character alignment.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The characters that have this alignment.
     */
    @OneToMany(mappedBy = "alignment")
    private List<CharacterSheet> characters;

    public CharacterAlignment() {
    }

    public CharacterAlignment(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets the unique identifier of the character alignment.
     * @return the unique identifier of the character alignment.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the unique identifier of the character alignment.
     * @param id the unique identifier of the character alignment.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the character alignment.
     * @return the name of the character alignment.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the character alignment.
     * @param name the name of the character alignment.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the character alignment.
     * @return the description of the character alignment.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the character alignment.
     * @param description the description of the character alignment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Compares this character alignment to another object.
     * @param o the object to compare this character alignment to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterAlignment characterAlignment = (CharacterAlignment) o;
        return id == characterAlignment.id &&
                name.equals(characterAlignment.name) &&
                description.equals(characterAlignment.description);
    }

    /**
     * Generates a hash code for the character alignment.
     * @return the hash code of the character alignment.
     */
    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * Gets a string representation of the character alignment.
     * @return a string representation of the character alignment.
     */
    @Override
    public String toString() {
        return "CharacterAlignment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
