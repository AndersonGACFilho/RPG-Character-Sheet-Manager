package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Story implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the story.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the story.
     */
    @Column(nullable = false)
    private String title;

    /**
     * Gets the unique identifier of the story.
     * @return the unique identifier of the story.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the story.
     * @param id the unique identifier of the story.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of the story.
     * @return the title of the story.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the story.
     * @param title the title of the story.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the story.
     * @return the description of the story.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the story.
     * @param description the description of the story.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The description of the story.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The game session to which this story belongs.
     */
    @ManyToOne
    @JoinColumn(name = "game_session_id")
    private GameSession gameSession;

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }
}