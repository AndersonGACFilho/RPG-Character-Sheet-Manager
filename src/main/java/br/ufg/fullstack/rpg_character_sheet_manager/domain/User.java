package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents a user in the RPG character sheet manager system.
 * A user can be either a player or a game master.
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
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The email of the user.
     */
    @Column(nullable = false, unique = true)
    private String email;

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
    @JsonManagedReference
    @ManyToMany()
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

    /**
     * Adds a game session where the user is a master.
     *
     * @param session the game session to be added
     */
    public void addSessionAsMaster(GameSession session) {
        sessionsAsMaster.add(session);
    }

    /**
     * Removes a game session where the user is a master.
     *
     * @param session the game session to be removed
     */
    public void removeSessionAsMaster(GameSession session) {
        sessionsAsMaster.remove(session);
    }

    /**
     * Adds a game session where the user is a player.
     *
     * @param session the game session to be added
     */
    public void addSessionAsPlayer(GameSession session) {
        sessionsAsPlayer.add(session);
    }

    /**
     * Removes a game session where the user is a player.
     *
     * @param session the game session to be removed
     */
    public void removeSessionAsPlayer(GameSession session) {
        sessionsAsPlayer.remove(session);
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<GameSession> getSessionsAsMaster() { return sessionsAsMaster; }
    public void setSessionsAsMaster(List<GameSession> sessionsAsMaster) { this.sessionsAsMaster = sessionsAsMaster; }

    public List<GameSession> getSessionsAsPlayer() { return sessionsAsPlayer; }
    public void setSessionsAsPlayer(List<GameSession> sessionsAsPlayer) { this.sessionsAsPlayer = sessionsAsPlayer; }

    public List<CharacterSheet> getCharacterSheets() { return characterSheets; }
    public void setCharacterSheets(List<CharacterSheet> characterSheets) { this.characterSheets = characterSheets; }


    public void addCharacterSheet(CharacterSheet characterSheet) {
        characterSheets.add(characterSheet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

}
