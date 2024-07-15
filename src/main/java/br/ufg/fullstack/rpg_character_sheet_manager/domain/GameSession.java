package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
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
    private String name;

    /**
     * The description of the game session.
     */
    private String description;

    /**
     * The user who is the master of the game session.
     */
    @ManyToOne
    @JoinColumn(name = "master_id")
    private User master;

    /**
     * The list of character sheets in the game session.
     */
    @OneToMany(mappedBy = "session")
    @JsonIgnore
    private List<CharacterSheet> characterSheets;

    /**
     * The list of stories in the game session.
     */
    @OneToMany(mappedBy = "gameSession")
    @JsonIgnore
    private List<Story> stories;

    /**
     * The list of game rules in the game session.
     */
    @OneToMany(mappedBy = "gameSession")
    @JsonIgnore
    private List<GameRule> gameRules;
    /**
     * The list of users who are players in the game session.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "sessionsAsPlayer")
    private List<User> players;

    /**
     * The list of character alignments associated with the game session.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "gameSession")
    private List<CharacterAlignment> characterAlignments;

    /**
     * TODO Add the Items to the GameSession to turn the master able to create
     * items and assign them to the players
     */

    // Getters and setters
    /**
     * Gets the unique identifier of the game session.
     * @return the unique identifier of the game session.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the game session.
     * @param id the unique identifier of the game session.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the game session.
     * @return the name of the game session.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the game session.
     * @param name the name of the game session.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the game session.
     * @return the description of the game session.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the game session.
     * @param description the description of the game session.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the user who is the master of the game session.
     * @return the user who is the master of the game session.
     */
    public User getMaster() {
        return master;
    }

    /**
     * Sets the user who is the master of the game session.
     * @param master the user who is the master of the game session.
     */
    public void setMaster(User master) {
        this.master = master;
    }

    /**
     * Gets the list of character sheets in the game session.
     * @return the list of character sheets in the game session.
     */
    public List<CharacterSheet> getCharacterSheets() {
        return characterSheets;
    }

    /**
     * Sets the list of character sheets in the game session.
     * @param characterSheets the list of character sheets in the game session.
     */
    public void setCharacterSheets(List<CharacterSheet> characterSheets) {
        this.characterSheets = characterSheets;
    }

    /**
     * Gets the list of stories in the game session.
     * @return the list of stories in the game session.
     */
    public List<Story> getStories() {
        return stories;
    }

    /**
     * Sets the list of stories in the game session.
     * @param stories the list of stories in the game session.
     */
    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    /**
     * Gets the list of game rules in the game session.
     * @return the list of game rules in the game session.
     */
    public List<GameRule> getGameRules() {
        return gameRules;
    }

    /**
     * Sets the list of game rules in the game session.
     * @param gameRules the list of game rules in the game session.
     */
    public void setGameRules(List<GameRule> gameRules) {
        this.gameRules = gameRules;
    }

    /**
     * Gets the list of users who are players in the game session.
     * @return the list of users who are players in the game session.
     */
    public List<User> getPlayers() {
        return players;
    }

    /**
     * Sets the list of users who are players in the game session.
     * @param players the list of users who are players in the game session.
     */
    public void setPlayers(List<User> players) {
        this.players = players;
    }

    /**
     * Gets the character alignments associated with the game session.
     * @return the character alignments associated with the game session.
     */
    public List<CharacterAlignment> getCharacterAlignments() {
        return characterAlignments;
    }

    /**
     * Sets the character alignments associated with the game session.
     * @param characterAlignments the character alignments associated with the game session.
     */
    public void setCharacterAlignments(List<CharacterAlignment> characterAlignments) {
        this.characterAlignments = characterAlignments;
    }

    // Add and remove players
    /**
     * Adds a player to the game session.
     * @param player the player to be added.
     */
    public void addPlayer(User player) {
        players.add(player);
    }

    /**
     * Removes a player from the game session.
     * @param player the player to be removed.
     */
    public void removePlayer(User player) {
        players.remove(player);
    }

    // Add and Remove game rules
    /**
     * Adds a game rule to the game session.
     * @param gameRule the game rule to be added.
     */
    public void addGameRule(GameRule gameRule) {
        gameRules.add(gameRule);
    }

    /**
     * Removes a game rule from the game session.
     * @param gameRule the game rule to be removed.
     */
    public void removeGameRule(GameRule gameRule) {
        gameRules.remove(gameRule);
    }

    // Add and remove stories
    /**
     * Adds a story to the game session.
     * @param existingStory the story to be added.
     */
    public void removeStory(Story existingStory) {
        stories.remove(existingStory);
    }

    /**
     * Removes a story from the game session.
     * @param story the story to be removed.
     */
    public void addStory(Story story) {
        stories.add(story);
    }

    // Add and remove character sheets
    /**
     * Adds a character sheet to the game session.
     * @param characterSheet the character sheet to be added.
     */
    public void addCharacterSheet(CharacterSheet characterSheet) {
        characterSheets.add(characterSheet);
    }

    /**
     * Removes a character sheet from the game session.
     * @param characterSheet the character sheet to be removed.
     */
    public void removeCharacterSheet(CharacterSheet characterSheet) {
        characterSheets.remove(characterSheet);
    }

    // Add and remove character alignments
    /**
     * Adds a character alignment to the game session.
     * @param characterAlignment the character alignment to be added.
     */
    public void addCharacterAlignment(CharacterAlignment characterAlignment) {
        characterAlignments.add(characterAlignment);
    }

    /**
     * Removes a character alignment from the game session.
     * @param characterAlignment the character alignment to be removed.
     */
    public void removeCharacterAlignment(CharacterAlignment characterAlignment){
        characterAlignments.remove(characterAlignment);
    }

    // Equals and hashCode

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
