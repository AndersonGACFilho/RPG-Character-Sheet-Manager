package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.exceptions.ResourceNotFoundException;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing users.
 * This class handles the business logic related to users.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all users and converts them to Users.
     * @param page the page number
     * @return a list of Users
     */

    public Page<User> getAllUsers(Integer page) {
        PageRequest pageable = PageRequest.of(page, 10);
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
     *
     * @param id the user ID
     */
    public void deleteUser(Long id) {
        try{
            // Delete the User entity by ID from the repository
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // If the User entity is still referenced by another entity, throw a
            // ResourceNotFoundException
            throw new ResourceNotFoundException("User is still referenced by another entity");
        }
    }
}
