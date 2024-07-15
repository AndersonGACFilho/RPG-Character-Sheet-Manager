package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Controller for managing users.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieves all users with pagination.
     * @param page the page number
     * @param size the number of users per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of Users with pagination
     */
    @GetMapping("/page")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order)
    {
        Page<User> users = userService.getAllUsers(page, size, sortBy, order);
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieves user by ID.
     * @param id the user ID
     * @return a User
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
    {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Creates a new user.
     * @param user the User to create
     * @return a ResponseEntity with the location of the created user
     */
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user)
    {
        User createdUser = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Updates an existing user.
     * @param user the User to update
     * @param id the user ID
     * @return a ResponseEntity with status 204 No Content
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @RequestBody User user,
            @PathVariable Long id)
    {
        userService.updateUser(user, id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a user by ID.
     * @param id the user ID
     * @return a ResponseEntity with status 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
