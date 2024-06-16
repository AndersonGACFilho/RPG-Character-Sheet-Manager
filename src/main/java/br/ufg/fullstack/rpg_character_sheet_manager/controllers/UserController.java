package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.dtos.UserDTO;
import br.ufg.fullstack.rpg_character_sheet_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves all users.
     *
     * @return a list of UserDTOs
     */
    @GetMapping
    public List<UserDTO> getAllUsers() {
        // Call the service to get all users
        return userService.getAllUsers();
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return a ResponseEntity containing the UserDTO if found, or 404 Not
     * Found if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        // Call the service to get the user by ID
        Optional<UserDTO> user = userService.getUserById(id);
        // Return the user if found, or 404 Not Found if not found
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new user.
     *
     * @param userDTO the UserDTO
     * @return a ResponseEntity containing the created UserDTO
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        // Call the service to create a new user
        UserDTO savedUser = userService.createUser(userDTO);
        // Return the created user
        return ResponseEntity.ok(savedUser);
    }

    /**
     * Updates an existing user.
     *
     * @param id the user ID
     * @param userDetails the UserDTO with updated data
     * @return a ResponseEntity containing the updated UserDTO if the user was
     * found, or 404 Not Found if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @RequestBody UserDTO userDetails) {
        // Call the service to update the user
        Optional<UserDTO> updatedUser = userService.updateUser(id, userDetails);
        // Return the updated user if found, or 404 Not Found if not found
        return updatedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the user ID
     * @return a ResponseEntity with status 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // Call the service to delete the user
        userService.deleteUser(id);
        // Return a 200 OK response
        return ResponseEntity.ok().build();
    }
}
