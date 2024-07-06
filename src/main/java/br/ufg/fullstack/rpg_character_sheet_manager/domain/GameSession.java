package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Represents a game session in the RPG character sheet manager system.
 * A game session is managed by a game master and includes multiple players and character sheets.
 */
@Entity
public class GameSession {

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
    @JsonBackReference
    @ManyToMany(mappedBy = "sessionsAsPlayer")
    private List<User> players;

    /**
     * The list of character sheets associated with the game session.
     */
    @OneToMany(mappedBy = "session")
    private List<CharacterSheet> characterSheets;

    /**
     * The list of stories associated with the game session.
     */
    @OneToMany(mappedBy = "gameSession")
    private List<Story> stories;

    /**
     * The list of game rules associated with the game session.
     */
    @OneToMany(mappedBy = "gameSession")
    private List<GameRule> gameRules;

    ;

    /**
     * TODO Add the Items to the GameSession to turn the master able to create items and
     * assign them to the players
     */

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public User getMaster() { return master; }
    public void setMaster(User master) { this.master = master; }

    public List<User> getPlayers() { return players; }
    public void setPlayers(List<User> players) { this.players = players; }

    public List<CharacterSheet> getCharacterSheets() { return characterSheets; }
    public void setCharacterSheets(List<CharacterSheet> characterSheets) { this.characterSheets = characterSheets; }

    public List<Story> getStories() { return stories; }
    public void setStories(List<Story> stories) { this.stories = stories; }

    public List<GameRule> getGameRules() { return gameRules; }
    public void setGameRules(List<GameRule> gameRules) { this.gameRules = gameRules; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSession that = (GameSession) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(master, that.master);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, master);
    }
}
