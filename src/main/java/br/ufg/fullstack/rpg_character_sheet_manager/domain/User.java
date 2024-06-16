package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents a user in the system.
 */
@Entity(name = "person")
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * The password of the user.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The list of game sessions where the user is the master.
     */
    @OneToMany(mappedBy = "master")
    private List<GameSession> sessionsAsMaster;

    /**
     * The list of game sessions where the user is a player.
     */
    @ManyToMany
    @JoinTable(
            name = "user_session",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id")
    )
    private List<GameSession> sessionsAsPlayer;

    /**
     * The list of character sheets owned by the user.
     */
    @OneToMany(mappedBy = "owner")
    private List<CharacterSheet> characterSheets;

    // Getters and setters

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user ID.
     *
     * @param id the user ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the game sessions where the user is the master.
     *
     * @return the game sessions where the user is the master
     */
    public List<GameSession> getSessionsAsMaster() {
        return sessionsAsMaster;
    }

    /**
     * Sets the game sessions where the user is the master.
     *
     * @param sessionsAsMaster the game sessions where the user is the master
     */
    public void setSessionsAsMaster(List<GameSession> sessionsAsMaster) {
        this.sessionsAsMaster = sessionsAsMaster;
    }

    /**
     * Gets the game sessions where the user is a player.
     *
     * @return the game sessions where the user is a player
     */
    public List<GameSession> getSessionsAsPlayer() {
        return sessionsAsPlayer;
    }

    /**
     * Sets the game sessions where the user is a player.
     *
     * @param sessionsAsPlayer the game sessions where the user is a player
     */
    public void setSessionsAsPlayer(List<GameSession> sessionsAsPlayer) {
        this.sessionsAsPlayer = sessionsAsPlayer;
    }

    /**
     * Gets the character sheets owned by the user.
     *
     * @return the character sheets owned by the user
     */
    public List<CharacterSheet> getCharacterSheets() {
        return characterSheets;
    }

    /**
     * Sets the character sheets owned by the user.
     *
     * @param characterSheets the character sheets owned by the user
     */
    public void setCharacterSheets(List<CharacterSheet> characterSheets) {
        this.characterSheets = characterSheets;
    }

    /**
     * Checks if this user is equal to another object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        // Check if the object is compared with itself
        if (this == o) return true;
        // Check if the object is an instance of User
        if (o == null || getClass() != o.getClass()) return false;
        // Typecast the object to User
        User user = (User) o;
        // Compare the fields for equality
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    /**
     * Generates a hash code for this user.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        // Generate a hash code using the id, username, and password fields
        return Objects.hash(id, username, password);
    }
}
