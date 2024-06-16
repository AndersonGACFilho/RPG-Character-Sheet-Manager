package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents a game session in the system.
 */
@Entity
public class GameSession {

    /**
     * The unique identifier for the game session.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the game session.
     */
    @Column(nullable = false)
    private String name;

    /**
     * A brief description of the game session.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The user who is the master of the game session.
     */
    @ManyToOne
    @JoinColumn(name = "master_id", nullable = false)
    private User master;

    /**
     * The list of users who are players in the game session.
     */
    @ManyToMany(mappedBy = "sessionsAsPlayer")
    private List<User> players;

    /**
     * The list of character sheets associated with the game session.
     */
    @OneToMany(mappedBy = "session")
    private List<CharacterSheet> characterSheets;

    // Getters and setters

    /**
     * Gets the game session ID.
     *
     * @return the game session ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the game session ID.
     *
     * @param id the game session ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the game session.
     *
     * @return the name of the game session
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the game session.
     *
     * @param name the name of the game session
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the game session.
     *
     * @return the description of the game session
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the game session.
     *
     * @param description the description of the game session
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the user who is the master of the game session.
     *
     * @return the user who is the master of the game session
     */
    public User getMaster() {
        return master;
    }

    /**
     * Sets the user who is the master of the game session.
     *
     * @param master the user who is the master of the game session
     */
    public void setMaster(User master) {
        this.master = master;
    }

    /**
     * Gets the list of users who are players in the game session.
     *
     * @return the list of users who are players in the game session
     */
    public List<User> getPlayers() {
        return players;
    }

    /**
     * Sets the list of users who are players in the game session.
     *
     * @param players the list of users who are players in the game session
     */
    public void setPlayers(List<User> players) {
        this.players = players;
    }

    /**
     * Gets the list of character sheets associated with the game session.
     *
     * @return the list of character sheets associated with the game session
     */
    public List<CharacterSheet> getCharacterSheets() {
        return characterSheets;
    }

    /**
     * Sets the list of character sheets associated with the game session.
     *
     * @param characterSheets the list of character sheets associated with the
     * game session
     */
    public void setCharacterSheets(List<CharacterSheet> characterSheets) {
        this.characterSheets = characterSheets;
    }

    /**
     * Checks if this game session is equal to another object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        // Check if the object is compared with itself
        if (this == o) return true;
        // Check if the object is an instance of GameSession
        if (o == null || getClass() != o.getClass()) return false;
        // Typecast the object to GameSession
        GameSession that = (GameSession) o;
        // Compare the fields for equality
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(master, that.master);
    }

    /**
     * Generates a hash code for this game session.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        // Generate a hash code using the id, name, description, and master fields
        return Objects.hash(id, name, description, master);
    }
}
