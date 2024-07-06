package br.ufg.fullstack.rpg_character_sheet_manager.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CharacterSheet implements Serializable{
    private static final long serialVersionUID = 1L;

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
     * The description of the character.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The background of the character.
     */
    @Column(nullable = false)
    private String background;

    /**
     * The alignment of the character.
     */
    @ManyToOne
    private CharacterAlignment alignment;

    /**
     * Status of the character, if it is alive or dead.
     */
    @Column(nullable = false)
    private boolean alive;

    /**
     * The type of the character, if it is a player or a non-player character.
     */
    @Column(nullable = false)
    private boolean player;

    /**
     * The user that owns the character.
     */
    @ManyToOne
    private User owner;

    /**
     * The game session where the character is being played.
     */
    @ManyToOne
    private GameSession session;

    // Getters and setters

    /**
     * Gets the unique identifier of the character sheet.
     * @return the unique identifier of the character sheet.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the character sheet.
     * @param id the unique identifier of the character sheet.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the character.
     * @return the name of the character.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the character.
     * @param name the name of the character.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the character.
     * @return the description of the character.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the character.
     * @param description the description of the character.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the background of the character.
     * @return the background of the character.
     */
    public String getBackground() {
        return background;
    }

    /**
     * Sets the background of the character.
     * @param background the background of the character.
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * Gets the alignment of the character.
     * @return the alignment of the character.
     */
    public CharacterAlignment getAlignment() {
        return alignment;
    }

    /**
     * Sets the alignment of the character.
     * @param alignment the alignment of the character.
     */
    public void setAlignment(CharacterAlignment alignment) {
        this.alignment = alignment;
    }

    /**
     * Gets the status of the character, if it is alive or dead.
     * @return the status of the character, if it is alive or dead.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets the status of the character, if it is alive or dead.
     * @param alive the status of the character, if it is alive or dead.
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Gets the type of the character, if it is a player or a non-player character.
     * @return the type of the character, if it is a player or a non-player character.
     */
    public boolean isPlayer() {
        return player;
    }

    /**
     * Sets the type of the character, if it is a player or a non-player character.
     * @param player the type of the character, if it is a player or a non-player character.
     */
    public void setPlayer(boolean player) {
        this.player = player;
    }

    /**
     * Gets the user that owns the character.
     * @return the user that owns the character.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the user that owns the character.
     * @param owner the user that owns the character.
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets the game session where the character is being played.
     * @return the game session where the character is being played.
     */
    public GameSession getSession() {
        return session;
    }

    /**
     * Sets the game session where the character is being played.
     * @param session the game session where the character is being played.
     */
    public void setSession(GameSession session) {
        this.session = session;
    }

    // Equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterSheet that = (CharacterSheet) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}