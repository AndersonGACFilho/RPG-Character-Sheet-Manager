package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Represents a character sheet in the system.
 */
@Entity
public class CharacterSheet {

    /**
     * The unique identifier for the character sheet.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the character.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The user who owns the character sheet.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    /**
     * The game session to which the character sheet belongs.
     */
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private GameSession session;

    // Getters and setters

    /**
     * Gets the character sheet ID.
     *
     * @return the character sheet ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the character sheet ID.
     *
     * @param id the character sheet ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the character.
     *
     * @return the name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the character.
     *
     * @param name the name of the character
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user who owns the character sheet.
     *
     * @return the user who owns the character sheet
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the user who owns the character sheet.
     *
     * @param owner the user who owns the character sheet
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets the game session to which the character sheet belongs.
     *
     * @return the game session to which the character sheet belongs
     */
    public GameSession getSession() {
        return session;
    }

    /**
     * Sets the game session to which the character sheet belongs.
     *
     * @param session the game session to which the character sheet belongs
     */
    public void setSession(GameSession session) {
        this.session = session;
    }

    /**
     * Checks if this character sheet is equal to another object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        // Check if the object is compared with itself
        if (this == o) return true;
        // Check if the object is an instance of CharacterSheet
        if (o == null || getClass() != o.getClass()) return false;
        // Typecast the object to CharacterSheet
        CharacterSheet that = (CharacterSheet) o;
        // Compare the fields for equality
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(session, that.session);
    }

    /**
     * Generates a hash code for this character sheet.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        // Generate a hash code using the id, name, owner, and session fields
        return Objects.hash(id, name, owner, session);
    }
}
