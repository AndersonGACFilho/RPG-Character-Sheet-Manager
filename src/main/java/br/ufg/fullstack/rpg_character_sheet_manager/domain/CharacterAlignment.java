package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 * Represents the alignment of a character in the RPG.
 */
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
    @JsonIgnore
    private List<CharacterSheet> characters;

    /**
     * The game session to which this character alignment belongs.
     */
    @ManyToOne
    @JsonIgnore
    private GameSession gameSession;

    public CharacterAlignment() {
    }

    public CharacterAlignment(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets the unique identifier of the character alignment.
     *
     * @return the unique identifier of the character alignment.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the unique identifier of the character alignment.
     *
     * @param id the unique identifier of the character alignment.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the character alignment.
     *
     * @return the name of the character alignment.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the character alignment.
     *
     * @param name the name of the character alignment.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the character alignment.
     *
     * @return the description of the character alignment.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the character alignment.
     *
     * @param description the description of the character alignment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the characters that have this alignment.
     *
     * @return the characters that have this alignment.
     */
    public List<CharacterSheet> getCharacters() {
        return characters;
    }

    /**
     * Sets the characters that have this alignment.
     *
     * @param characters the characters that have this alignment.
     */
    public void setCharacters(List<CharacterSheet> characters) {
        this.characters = characters;
    }

    /**
     * Gets the game session to which this character alignment belongs.
     * @return the game session to which this character alignment belongs.
     */
    public GameSession getGameSession() {
        return gameSession;
    }

    /**
     * Sets the game session to which this character alignment belongs.
     * @param gameSession the game session to which this character alignment belongs.
     */
    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    // Adders and removers
    /**
     * Adds a character to the list of characters that have this alignment.
     * @param character the character to add to the list of characters that have this alignment.
     */
    public void addCharacter(CharacterSheet character) {
        characters.add(character);
    }

    /**
     * Removes a character from the list of characters that have this alignment.
     * @param character the character to remove from the list of characters that have this alignment.
     */
    public void removeCharacter(CharacterSheet character) {
        characters.remove(character);
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
