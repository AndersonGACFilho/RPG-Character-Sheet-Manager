package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterSheet;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the service for the User entity.
 * <p>
 * This class provides methods to interact with the User entity.
 */
@Service
public class UserService {

    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    /**
     * The repository for the user entity.
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * The character sheet service.
     */
    @Autowired
    private CharacterSheetService characterSheetService;
    /**
     * The game session service.
     */
    @Autowired
    private GameSessionService gameSessionService;

    public User getUser() {
        // Get the authenticated user
        logger.info("Getting the authenticated user");
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        // Get the email of the authenticated user
        logger.info("Getting the email of the authenticated user");
        String authenticatedUserEmail = authentication.getName();
        // Get the user by the email
        logger.info("Getting the user by the email");
        return userRepository.findByEmail(authenticatedUserEmail)
            .orElseThrow(() ->
            new ResourceNotFoundException("User not found with email: " + authenticatedUserEmail));
    }

    /**
     * Get all users.
     * @param page of the page (number of the page).
     * @param size of the page (number of users).
     * @param sort of the page (field name).
     * @param direction of the page (ASC or DESC).
     * @return Page of users.
     */
    public Page<User> getAllUsers(
        int page, int size, String sort, String direction)
    {
        // Create a pageable object
        logger.info("Creating a pageable object");
        PageRequest pageable = PageRequest.of(page, size,
            Sort.Direction.fromString(direction), sort);
        // Get all users
        return userRepository.findAll(pageable);
    }

    /**
     * Creates a new user.
     *
     * @return The created user.
     */
    public User createUser(User user) {
        try {
            // Save the user
            logger.info("Saving the user");
            logger.info("User: " + user.getEmail());
            // Hash the password
            logger.info("Hashing the password");
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            // Save the user
            logger.info("Saving the user");
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error saving the user");
            logger.error(e.getMessage());
            throw new DataIntegrityViolationException("The user already with email: " + user.getEmail(), e);
        }
    }

    /**
     * Gets the user by the user id.
     *
     * @param userId The user id.
     * @return The user with the given user id.
     */
    public User getUser(Long userId) {
        // Get the user by the user id
        logger.info("Getting the user by the user id");
        return userRepository.findById(userId).orElseThrow(
            () -> new ResourceNotFoundException("User not found with id " + userId));
    }

    /**
     * Updates the user.
     * @param user The user to be updated.
     * @return The updated user.
     */
    public User updateUser(User user) {
        // Get the existing user
        logger.info("Getting the existing user by the user id");
        User existingUser = getUser();
        // Set the id of the existing authenticated user to the new user
        logger.info("Setting the id of the existing logged-in user to the new user");
        user.setId(existingUser.getId());

        // Update the user
        logger.info("Updating the user by the authenticated user");
        return userRepository.save(user);
    }

    /**
     * Updates the user by ID.
     * @param user The user to be updated.
     * @return The updated user.
     */
    public User updateUser(User user, Long id) {
        // Get the existing user
        logger.info("Getting the existing user by ID");
        User existingUser = getUser(id);
        // Set the id of the existing user to the new user
        logger.info("Setting the id of the existing user to the new user");
        existingUser.setId(user.getId());

        // Update the user
        logger.info("Updating the user by ID");
        return userRepository.save(existingUser);
    }

    /**
     * Deletes a user currently logged in.
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteUser() {
        try {
            User user = getUser();

            // Remove all character sheets from the user
            logger.info("Removing all character sheets from the user");
            List<CharacterSheet> characterSheets =
                    new ArrayList<>(user.getCharacterSheets());
            for (CharacterSheet characterSheet : characterSheets) {
                logger.info("Deleting the character sheet");
                characterSheetService.deleteCharacterSheet(characterSheet.getId());
                user.getCharacterSheets().remove(characterSheet);
            }

            // Delete GameSessions where the user is a master
            List<GameSession> sessionsAsMaster =
                    new ArrayList<>(user.getSessionsAsMaster());
            for (GameSession session : sessionsAsMaster) {
                logger.info("Deleting the game session");
                gameSessionService.deleteGameSession(session.getId());
                user.getSessionsAsMaster().remove(session);
            }

            // Remove the user from all GameSessions where the user is a player
            List<GameSession> sessionsAsPlayer =
                    new ArrayList<>(user.getSessionsAsPlayer());
            for (GameSession session : sessionsAsPlayer) {
                logger.info("Removing the user from the game session");
                gameSessionService.removePlayerFromGameSession(user.getId(),
                        session.getId());
                user.getSessionsAsPlayer().remove(session);
            }

            // Finally, delete the user
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(
                    "User is still referenced by another entity", e);
        }
    }




    /**
     * Removes the logged-in user from a game session.
     * @param gameSessionId the game session ID
     */
    public void removePlayerFromGameSession(Long gameSessionId){
        try{
            logger.info("Removing the player from the game session");
            User user = getUser();
            // Find the game session by ID
            logger.info("Finding the game session by ID");
            GameSession gameSession = gameSessionService.
                getGameSessionById(gameSessionId);
            // Remove the user from the game session
            gameSession.removePlayer(user);
            // Save the game session
            gameSessionService.updateGameSession(gameSession, gameSessionId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    /**
     * Removes the logged-in user from a game session by IDs.
     * @param id the user ID
     * @param gameSessionId the game session ID
     */
    public void removePlayerFromGameSession(Long id, Long gameSessionId){
        try{
            User user = getUser();
            // Find the game session by ID
            GameSession gameSession = gameSessionService.
                    getGameSessionById(gameSessionId);
            // Remove the user from the game session
            gameSession.removePlayer(user);
            // Save the game session
            gameSessionService.updateGameSession(gameSession, gameSessionId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
