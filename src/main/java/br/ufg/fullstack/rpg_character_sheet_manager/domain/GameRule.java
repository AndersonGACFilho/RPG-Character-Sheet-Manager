package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class GameRule implements Serializable {

    /**
     * The unique identifier for the game rule.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the game rule.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The description of the game rule.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The name of the game rule.
     */
    @ManyToOne
    @JoinColumn(name = "game_session_id")
    private GameSession gameSession;

    public GameRule() {
    }

    public GameRule(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets the unique identifier of the game rule.
     * @return the unique identifier of the game rule.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the unique identifier of the game rule.
     * @param id the unique identifier of the game rule.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the game rule.
     * @return the name of the game rule.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the game rule.
     * @param name the name of the game rule.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the game rule.
     * @return the description of the game rule.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the game rule.
     * @param description the description of the game rule.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the game session to which the game rule belongs.
     * @return the game session to which the game rule belongs.
     */
    public GameSession getGameSession() {
        return gameSession;
    }

    /**
     * Sets the game session to which the game rule belongs.
     * @param gameSession the game session to which the game rule belongs.
     */
    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }
}