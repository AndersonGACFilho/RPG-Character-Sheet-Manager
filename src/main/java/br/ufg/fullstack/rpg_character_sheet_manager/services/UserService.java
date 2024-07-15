package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.CharacterSheet;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.GameSession;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 * This class handles the business logic related to users.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private CharacterSheetService characterSheetService;
    @Autowired
    @Lazy
    private GameSessionService gameSessionService;

    /**
     * Retrieves all users and converts them to Users.
     *
     * @param page   the page number
     * @param size  the number of users per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of Users
     */
    public Page<User> getAllUsers(Integer page, Integer size, String sortBy,
        String order) {
        PageRequest pageable = PageRequest.of(page, size,
            Sort.Direction.fromString(order), sortBy);
        return userRepository.findAll(pageable);
    }

    /**
     * Retrieves a user by ID and converts it to a User.
     *
     * @param id the user ID
     * @return an Optional containing the User if found, or empty if not
     * found
     */
    public User getUserById(Long id) {
        // Fetch the User entity by ID from the repository
        Optional<User> user = userRepository.findById(id);
        // If the User entity was found, convert it to a User and return it
        // If the User entity was not found, throw a ResourceNotFoundException
        return user.orElseThrow( () -> new ResourceNotFoundException("User not found"));
    }

    /**
     * Creates a new user and converts it to a User.
     *
     * @param user the User
     * @return the created User
     */
    public User createUser(User user) {
        // Save the User entity to the repository
        // Convert the saved User entity back to a User
        return userRepository.save(user);
    }

    /**
     * Updates an existing user and converts it to a User.
     *
     * @param updatedUser the User with updated data
     * @param id
     */
    public void updateUser(User updatedUser, Long id) {
        // Force the User entity to have the ID of the UserDTO
        updatedUser.setId(id);
        // get the User entity by ID from the service
        User user = getUserById(id);
        // save the updated User entity to the repository
        userRepository.save(updatedUser);
    }

    /**
     * Deletes a user by ID.
     * @param id the user ID
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteUser(Long id) {
        try {
            User user = getUserById(id);

            // Remove all character sheets from the user
            List<CharacterSheet> characterSheets =
                    new ArrayList<>(user.getCharacterSheets());
            for (CharacterSheet characterSheet : characterSheets) {
                characterSheetService.deleteCharacterSheet(characterSheet.getId());
                user.getCharacterSheets().remove(characterSheet);
            }

            // Delete GameSessions where the user is a master
            List<GameSession> sessionsAsMaster =
                    new ArrayList<>(user.getSessionsAsMaster());
            for (GameSession session : sessionsAsMaster) {
                gameSessionService.deleteGameSession(session.getId());
                user.getSessionsAsMaster().remove(session);
            }

            // Remove the user from all GameSessions where the user is a player
            List<GameSession> sessionsAsPlayer =
                    new ArrayList<>(user.getSessionsAsPlayer());
            for (GameSession session : sessionsAsPlayer) {
                gameSessionService.removePlayerFromGameSession(user.getId(),
                        session.getId());
                user.getSessionsAsPlayer().remove(session);
            }

            // Finally delete the user
            userRepository.delete(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(
                    "User is still referenced by another entity", e);
        }
    }




    /**
     * Adds a user as a player to a game session.
     * @param id the user ID
     * @param gameSessionId the game session ID
     */
    public void removePlayerFromGameSession(Long id, Long gameSessionId){
        try{
            // Find the user by ID
            User user = getUserById(id);
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
