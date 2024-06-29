package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/page/{page}")
    public Page<User> getAllUsers(@PathVariable Integer page) {
        // Call the service to get all users
        return userService.getAllUsers(page);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return a ResponseEntity containing the UserDTO if found, or 404 Not
     * Found if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Call the service to get the user by ID
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Creates a new user.
     *
     * @param user the User to create
     * @return a ResponseEntity containing the created user
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Call the service to create a new user
        User savedUser = userService.createUser(user);
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
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody User userDetails) {
        // Call the service to update the user
        User updatedUser = userService.updateUser(userDetails);
        // Return the updated user
        return ResponseEntity.ok(updatedUser);
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
