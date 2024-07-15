package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Represents a story in the RPG character sheet manager system.
 * A story belongs to a game session and contains narrative elements for the session.
 */
@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the story.
     */
    private String title;

    /**
     * The description of the story.
     */
    private String description;

    /**
     * The game session the story is part of.
     */
    @ManyToOne
    @JoinColumn(name = "game_session_id")
    @JsonIgnore
    private GameSession gameSession;

    // Getters and Setters
    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}