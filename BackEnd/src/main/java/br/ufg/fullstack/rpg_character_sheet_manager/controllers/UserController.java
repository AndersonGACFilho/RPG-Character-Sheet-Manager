package br.ufg.fullstack.rpg_character_sheet_manager.controllers;

import br.ufg.fullstack.rpg_character_sheet_manager.configs.JwtAuthenticationFilter;
import br.ufg.fullstack.rpg_character_sheet_manager.domain.User;
import br.ufg.fullstack.rpg_character_sheet_manager.dtos.LoginRequest;
import br.ufg.fullstack.rpg_character_sheet_manager.dtos.UserDto;
import br.ufg.fullstack.rpg_character_sheet_manager.services.AuthenticationService;
import br.ufg.fullstack.rpg_character_sheet_manager.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * The user service.
     */
    @Autowired
    private UserService userService;

    /**
     * The authentication service.
     */
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Creates a new user.
     * @param user the User to create
     * @return a ResponseEntity with the location of the created user
     */
    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody User user)
    {
        logger.info("Creating user with email: " + user.getEmail());
        User createdUser = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Logs in a user.
     * @param user the login request
     * @return a ResponseEntity with the JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest user)
    {
        logger.info("Logging in user with email: " + user.getEmail());
        String token = authenticationService.authenticateUser(user);
        return ResponseEntity.ok(token);
    }

    /**
     * Retrieves user currently logged in.
     *
     * @return a User
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser()
    {
        logger.info("Getting the authenticated user");
        User user = userService.getUser();
        return ResponseEntity.ok(new UserDto(user));
    }

    /**
     * Retrieves all users with pagination.
     * @param page the page number
     * @param size the number of users per page
     * @param sortBy the field to sort by
     * @param order the sort order
     * @return a list of Users with pagination
     */
    @GetMapping("/page")
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order)
    {
        logger.info("Getting all users");
        Page<User> users = userService.getAllUsers(page, size, sortBy, order);
        return ResponseEntity.ok(users.map(UserDto::new));
    }
    /**
     * Updates an existing logged-in user.
     * @param user the User to update
     * @return a ResponseEntity with status 204 No Content
     */
    @PutMapping("/me")
    public ResponseEntity<Void> updateUser(
            @RequestBody User user)
    {
        logger.info("Updating user with id: " + user.getId());
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a user currently logged in.
     * @return a ResponseEntity with status 204 No Content
     */
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser()
    {
        userService.deleteUser();
        return ResponseEntity.noContent().build();
    }
}
