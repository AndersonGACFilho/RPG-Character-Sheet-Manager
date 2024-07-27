package br.ufg.fullstack.rpg_character_sheet_manager.dtos;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;

import java.io.Serializable;

/**
 * @brief Data Transfer Object for User
 * @details This class is responsible for the data transfer of User objects.
 * It is used to send User data to the client, without sending sensitive
 * information like passwords.
 * @see br.ufg.fullstack.rpg_character_sheet_manager.domain.User
 */
public class UserDto implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the user.
     */
    private Long id;

    /**
     * The username of the user.
     */
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
    private String email;

    /**
     * Default constructor
     */
    public UserDto() {
    }

    /**
     * Constructor with parameters
     * @param user the User object to be converted to a UserDto
     */
    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }

    /**
     * Get the user ID
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the user ID
     * @param id the user ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the username of the user
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the first name of the user
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name of the user
     * @param firstName the first name of the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of the user
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name of the user
     * @param lastName the last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the email of the user
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the user
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
