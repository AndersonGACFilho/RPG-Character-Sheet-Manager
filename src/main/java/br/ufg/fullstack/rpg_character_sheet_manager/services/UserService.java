package br.ufg.fullstack.rpg_character_sheet_manager.services;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.dtos.UserDTO;
import br.ufg.fullstack.rpg_character_sheet_manager.mappers.UserMapper;
import br.ufg.fullstack.rpg_character_sheet_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 * This class handles the business logic related to users.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    /**
     * Retrieves all users and converts them to UserDTOs.
     *
     * @return a list of UserDTOs
     */
    public List<UserDTO> getAllUsers() {
        // Fetch all User entities from the repository
        return userRepository.findAll().stream()
                // Convert each User entity to a UserDTO
                .map(userMapper::toDto)
                // Collect the results into a list
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a user by ID and converts it to a UserDTO.
     *
     * @param id the user ID
     * @return an Optional containing the UserDTO if found, or empty if not
     * found
     */
    public Optional<UserDTO> getUserById(Long id) {
        // Fetch the User entity by ID from the repository
        return userRepository.findById(id)
                // Convert the User entity to a UserDTO
                .map(userMapper::toDto);
    }

    /**
     * Creates a new user and converts it to a UserDTO.
     *
     * @param userDTO the UserDTO
     * @return the created UserDTO
     */
    public UserDTO createUser(UserDTO userDTO) {
        // Convert the UserDTO to a User entity
        User user = userMapper.toEntity(userDTO);
        // Save the User entity to the repository
        User savedUser = userRepository.save(user);
        // Convert the saved User entity back to a UserDTO
        return userMapper.toDto(savedUser);
    }

    /**
     * Updates an existing user and converts it to a UserDTO.
     *
     * @param id the user ID
     * @param userDTO the UserDTO with updated data
     * @return an Optional containing the updated UserDTO if the user was found,
     * or empty if not found
     */
    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
        // Fetch the existing User entity by ID from the repository
        return userRepository.findById(id).map(existingUser -> {
            // Update the existing User entity with new data
            existingUser.setUsername(userDTO.getUsername());
            existingUser.setPassword(userDTO.getPassword());
            // Save the updated User entity to the repository
            User updatedUser = userRepository.save(existingUser);
            // Convert the updated User entity back to a UserDTO
            return userMapper.toDto(updatedUser);
        });
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the user ID
     */
    public void deleteUser(Long id) {
        // Delete the User entity by ID from the repository
        userRepository.deleteById(id);
    }
}
